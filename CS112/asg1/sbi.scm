;; Michael Luong #mcluong@ucsc.edu
;; Sudeep Baniya #sucbaniy@ucsc.edu
;;/afs/cats.ucsc.edu/courses/cmps112-wm/usr/racket/bin/mzscheme -qr
;; $Id: sbi.scm,v 1.4 2018-04-11 16:31:36-07 - - $
;;
;; NAME
;;    sbi.scm - silly basic interpreter
;;
;; SYNOPSIS
;;    sbi.scm filename.sbir
;;
;; DESCRIPTION
;;    The file mentioned in argv[1] is read and assumed to be an SBIR
;;    program, which is the executed.  Currently it is only printed.
;;
 
(define *stdin* (current-input-port))
(define *stdout* (current-output-port))
(define *stderr* (current-error-port))
 
(define *run-file*
    (let-values
        (((dirpath basepath root?)
            (split-path (find-system-path 'run-file))))
        (path->string basepath))
)
 
(define (die list)
    (for-each (lambda (item) (display item *stderr*)) list)
    (newline *stderr*)
    (exit 1)
)
 
(define (usage-exit)
    (die `("Usage: " ,*run-file* " filename"))
)
 
(define (readlist-from-inputfile filename)
    (let ((inputfile (open-input-file filename)))
         (if (not (input-port? inputfile))
             (die `(,*run-file* ": " ,filename ": open failed"))
             (let ((program (read inputfile)))
                  (close-input-port inputfile)
                         program))))
 
(define (write-program-by-line filename program)
    (printf "==================================================~n")
    (printf "~a: ~s~n" *run-file* filename)
    (printf "==================================================~n")
    (printf "(~n")
    (map (lambda (line) (printf "~s~n" line)) program)
    (printf ")~n"))
 
(define (main arglist)
    (if (or (null? arglist) (not (null? (cdr arglist))))
        (usage-exit)
        (let* ((sbprogfile (car arglist))
               (program (readlist-from-inputfile sbprogfile)))
              (write-program-by-line sbprogfile program))))
 
(when (terminal-port? *stdin*)
      (main (vector->list (current-command-line-arguments))))

;-------------------------------------------

;fuction, label and variable hash tables
(define *function-table* (make-hash))
(define *label-table* (make-hash))
(define *variable-table*(make-hash))
 

(define (symbol-get key)
        (hash-ref *function-table* key))
(define (symbol-put! key value)
        (hash-set! *function-table* key value))

 ;symbol table initialize  
(hash-set! *variable-table* "pi"
     3.141592653589793238462643383279502884197169399)
(hash-set! *variable-table* "e"
     2.718281828459045235360287471352662497757247093)
(for-each
    (lambda (pair)
            (symbol-put! (car pair) (cadr pair)))
    `(
        (/ , (lambda (x y)
	(/ x (if (equal? y 0) 0.0 y ))))
        (%     ,(lambda (x y) (- x (* (quot x y) y))))
        (+ ,+)
        (- ,-)
        (* ,*)
        (abs ,abs)
        (^       ,(lambda (x y)
            (expt x y)))
        (log10_2 0.301029995663981195213738894724493026768189881)
        (sqrt_2  1.414213562373095048801688724209698078569671875)  
        (log10   ,(lambda (x)
            (/ (log x) (log 10.0))))
        (quot    ,(lambda (x y)
            (truncate (/ x y))))
        (ceil ,ceiling)
        (floor ,floor)
        (exp ,exp)
        (log     ,(lambda(x)
            (log (if (equal? x 0) 0.0 x))))
        (sqrt ,sqrt)
        (sin ,sin)  
        (cos ,cos)
        (tan ,tan)
        (asin ,asin)
        (acos ,acos)
        (round ,round)
        (atan    ,(lambda(x)
        (atan (if (equal? x 0) 0.0 x))))
        (tnc , truncate)
     ))

 ;; Expression evaluate function
(define (eval-expression expression)
    (if (sign? expression)
  (if (hash-has-key? *variable-table* expression) (hash-ref *variable-table* expression)
      (printf "~s DNE ~" expression)
  )
  (if (number? expression) expression
      (let (
      (oper (sign-get (car expression)))
      (tail-end (cdr expression)))
        (apply oper (map eval-expression tail-end)))
      )
  )
)

(define (set-label-table border)
    (cond ((null? (cdr border)) (void)) ((pair? (cadr border)) (void))
          (else (hash-set! *label-table* (cadr border) border)))
 
)
 
(define (has-label list)
    (hash-has-key? *label-table* (cadr list))
    )
 
 ; if subroutine
(define (if-statement line lineNumber)
    (let ((oper (caadr line)))
        (cond ((eqv? oper '=)
            (if (eq? (eval-expression (cadadr line)) (eval-expression (car (cddadr line))))
                (car (hash-ref *label-table* (caddr line)))
                (+ lineNumber 1)
                ))
 
          ((eqv? oper '<)
          (if (< (eval-expression (cadadr line)) (eval-expression (car (cddadr line))))
                  (car (hash-ref *label-table* (caddr line)))
                  (+ lineNumber 1)
              ))
 
          ((eqv? oper '<=)
          (if (<= (eval-expression (cadadr line)) (eval-expression (car (cddadr line))))
                  (car (hash-ref *label-table* (caddr line)))
                  (+ lineNumber 1)
              ))
 
          ((eqv? op '>)
          (if (> (eval-expression (cadadr line)) (eval-expression (car (cddadr line))))
                  (car (hash-ref *label-table* (caddr line)))
                  (+ lineNumber 1)
              ))
 
          ((eqv? oper '>=)
          (if (>= (eval-expression (cadadr line)) (eval-expression (car (cddadr line))))
                  (car (hash-ref *label-table* (caddr line)))
                  (+ lineNumber 1)
              ))
 
          ((eqv? oper '<>)
          (if (not (eq? (eval-expression (cadadr line)) (eval-expression (car (cddadr line)))))
                  (car (hash-ref *label-table* (caddr line)))
                  (+ lineNumber 1)
              ))
 
          (else ( + lineNumber 1)))
          )
        )

; print statement
(define (print-statement line)
    (cond
       ((null? (cdr line)) (printf "~n"))
       ((string? (cadr line))
    (if (null? (cddr line))
      (printf "~s" (cadr line))
      (printf "~s~s" (cadr line)
      (eval-expression(caddr line)))
       )
     (printf "~n"))
       (else (printf "~s~n"(eval-expression(caddr line))))
    )
  )

; subroutine of dim, let, print, input
(define (eval-statement symbol)
    
    (if (eqv? (car symbol) 'dim)
        (hash-set! *variable-table* (cadr symbol)
        (make-vector (eval-expression (caaddr symbol))))
        (void)
    )
  
    (if (eqv? (car symbol) 'print)
    (print-statement symbol)
    (void)
    )
    (if (eqv? (car symbol) 'let)
  
    (if (pair? (cadr symbol))
        (if (vector? (hash-ref *variable-table* (caadr symbol)))
        (vector-set! (hash-ref *variable-table* (caadr symbol))
        (cadadr symbol) (eval-expression (caddr symbol)))
        (printf "Array does not exist~n")
        )
        (hash-set! *variable-table* (cadr symbol)
                  (eval-expression (caddr symbol)))
    )
    (void)
    )
    (if (eqv? (car symbol) 'input)
    (let ((in (read))) (hash-set! *variable-table* (cadr symbol) in))
    (void)
    )
)
 
 ;; function determines what it will do next
(define (sbin lineNumber code)
    (if (> lineNumber+1 (length code)) (exit 1)
    (void))
    (let ((border (list-ref code (- lineNumber 1))))

        (if (null? (cdr border)) (sbin (+ lineNumber 1) code)
        (void)
        )
       
        (if (has-label border)
        (let ((line (caddr border)))
            (if (eqv? (car line) 'goto) (sbin (car (hash-ref *label-table* (cadr line))) code)
            (void)
        )

        (if (eqv? (car line) 'if) (sbin (if-statement line lineNumber) code)
            (void)
        )
        (eval-statement line)
        (sbin (+ lineNumber 1) code)
        )
        (let ((line (cadr border)))
            (if (eqv? (car line) 'goto) (sbin (car (hash-ref *label-table* (cadr line))) code)
            (void)
        )
        (if (eqv? (car line ) 'if) (sbin (if-statement line lineNumber) code)
            (void)
        )
        (eval-statement line)
        (sbin (+ lineNumber 1) code)
        )
        )
    )
)
 
(main (vector->list (current-command-line-arguments)))
