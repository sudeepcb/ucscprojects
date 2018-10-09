(* $Id: bigint.ml,v 1.5 2014-11-11 15:06:24-08 - - $ *)

open Printf
open List

module Bigint = struct

    type sign     = Pos | Neg
    type bigint   = Bigint of sign * int list
    let  radix    = 10
    let  radixlen =  1

    let car       = List.hd
    let cdr       = List.tl
    let map       = List.map
    let reverse   = List.rev
    let strcat    = String.concat
    let strlen    = String.length
    let strsub    = String.sub
    let zero      = Bigint (Pos, [])

    let charlist_of_string str =
        let last = strlen str - 1
        in  let rec charlist pos result =
            if pos < 0
            then result
            else charlist (pos - 1) (str.[pos] :: result)
        in  charlist last []

    let bigint_of_string str =
        let len = strlen str
        in  let to_intlist first =
                let substr = strsub str first (len - first) in
                let digit char = int_of_char char - int_of_char '0' in
                map digit (reverse (charlist_of_string substr))
            in  if   len = 0
                then zero
                else if   str.[0] = '_'
                     then Bigint (Neg, to_intlist 1)
                     else Bigint (Pos, to_intlist 0)

    let string_of_bigint (Bigint (sign, value)) =
        match value with
        | []    -> "0"
        | value -> let reversed = reverse value
                   in  strcat ""
                       ((if sign = Pos then "" else "-") ::
                        (map string_of_int reversed))


    let trimzeros list =
        let rec trimzeros' list' = match list' with
            | []       -> []
            | [0]      -> []
            | car::cdr ->
               let cdr' = trimzeros' cdr in  match car, cdr' with
                   | 0, [] -> []
                   | car, cdr' -> car::cdr' in trimzeros' list

    let compare list1 list2 =
      if(length list1) < (length list2)
      then -1
      else if (length list1) > (length list2)
      then 1
      else (let rlist1 = rev list1
            in let rlist2 = rev list2
              in if rlist1 > rlist2
                  then 1
                  else if rlist2 > rlist1
                  then -1
                  else 0
    )
    let rec add' list1 list2 carry = match (list1, list2, carry) with
        | list1, [], 0       -> list1
        | [], list2, 0       -> list2
        | list1, [], carry   -> add' list1 [carry] 0
        | [], list2, carry   -> add' [carry] list2 0
        | car1::cdr1, car2::cdr2, carry ->
          let sum = car1 + car2 + carry
          in  sum mod radix :: add' cdr1 cdr2 (sum / radix)

    let rec sub' list1 list2 carry = match (list1, list2, carry) with
      | list1, [], 0		-> list1
      | [], list2, 0 		-> list2
      | list1, [], carry  -> trimzeros (sub' list1 [carry] 0)
      | [], list2, carry  -> trimzeros (sub' [carry] list2 0)
      | car1::cdr1, car2::cdr2, carry ->
    	if(car1 - carry) < car2
	then let diff = (10+car1) - (car2 + carry)
(* let var' = car 1 - carry:
  if var' => car2
then car1::car2 :: sub' cdr1 cdr2 0
 else
 car1' + radix - car2) *)
  in diff mod radix :: trimzeros(sub' cdr1 cdr2 1)
	else let diff = car1-car2-carry
	in diff mod radix :: trimzeros(sub' cdr1 cdr2 0)

  let double number = add' number number 0



  let add (Bigint (neg1, value1)) (Bigint (neg2, value2)) =
          if neg1 = neg2
          then Bigint (neg1, add' value1 value2 0)
          else if (neg1 = Neg && neg2 = Pos)
          then (
                if (compare value1 value2) >= 0
                then Bigint (neg2, trimzeros(sub' value2 value1 0))
                else Bigint (neg1, trimzeros(sub' value1 value2 0)))

          else if (neg1 = Pos && neg2 = Neg)
          then (
                if (compare value1 value2) >= 0
                then Bigint (neg1, trimzeros (sub' value1 value2 0))
                else Bigint (neg2, trimzeros (sub' value2 value1 0)))
          else zero



   let sub (Bigint (neg1, value1)) (Bigint (neg2, value2)) =
       if neg1 = neg2
        then (if (compare value1 value2) >= 0
             then Bigint (Pos, trimzeros (sub' value1 value2 0))
             else Bigint (Neg, trimzeros (sub' value2 value1 0)))
        else if neg2 = Neg
             then Bigint (Pos, add' value1 value2 0)
        else if neg1 = Neg
             then Bigint (Neg, add' value1 value2 0)
        else zero


  let rec mul' (mulp, power2, mulpcand') =
        if (compare power2 mulp) > 0
        then mulp, [0]
        else let remainder, product =
            mul' (mulp, double power2, double mulpcand')
            in if (compare power2 remainder) > 0
                then remainder, product
                else trimzeros (sub' remainder power2 0), add' product mulpcand' 0



  let mul_help (mulp, mulpcand) =
    let _, product = mul' (mulp, [1], mulpcand)
    in product             
   


    let mul (Bigint (neg1, mulp)) (Bigint (neg2, mulpcand)) =
        if neg1 = neg2
        then (if (compare mulp mulpcand) >= 0
              then Bigint (Pos, mul_help(mulp, mulpcand))
        else Bigint (Pos, mul_help(mulp, mulpcand)))
        else (if (compare mulp mulpcand) >= 0
              then Bigint (Neg, mul_help(mulp, mulpcand))
        else Bigint (Pos, mul_help(mulp, mulpcand)))


  let rec divrem' (dend, power2, dsor') =
      if (compare dsor' dend) > 0
      then [0], dend
      else let quotient, remainder =
               divrem' (dend, double power2, double dsor') in if
               (compare dsor' remainder) > 0
               then quotient, remainder
               else add' quotient power2 0, trimzeros(sub' remainder dsor' 0)

  let divrem (dend, dsor') = divrem' (dend, [1], dsor')


  let div_help(dend, dsor) =
    let quotient, _ = divrem (dend, dsor)
    in quotient


  let rem_help(dend, dsor) =
    let _, remainder = divrem (dend, dsor)
    in remainder


  let div (Bigint (neg1, dend)) (Bigint (neg2, dsor)) =
        if neg1 = neg2
        then (if (compare dend dsor) >=0
            then Bigint (Pos, div_help (dend, dsor))
            else Bigint (Pos, div_help (dend, dsor)))
        else (if (compare dend dsor) >= 0
            then Bigint (Neg, div_help (dend, dsor))
      else Bigint (Pos, div_help(dend, dsor)))


    let rem (Bigint (neg1, dend)) (Bigint (neg2, dsor)) =
        if neg1 = neg2
        then (if (compare dend dsor) >=0
              then Bigint (Pos, rem_help (dend, dsor))
        else Bigint (Pos, rem_help (dend, dsor)))
        else (if (compare dend dsor) >= 0
              then Bigint (Neg, rem_help (dend, dsor))
        else Bigint (Neg, rem_help (dend, dsor)))



  let even number =
        let remainder = trimzeros (rem_help (number, [2])) in
        if compare remainder [] = 0
            then true
            else false

  let rec pow'(base, expo, finalresult) =
      if compare expo [0] = 0
        then finalresult
          else if even expo
              then pow'(mul_help(base, base), div_help(expo, [2]), finalresult)
                 else pow'(base, sub' expo [1] 0, mul_help(base, finalresult))


    let pow (Bigint (neg1, base)) (Bigint (neg2, expo)) =
          if neg2 = Neg
            then zero
          else if neg1 = Pos
          then Bigint (Pos, pow'(base, expo, [1]))
          else if even expo
          then Bigint (Pos, pow'(base, expo, [1]))
          else Bigint (Neg, pow'(base, expo, [1]))

end
