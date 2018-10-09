/*
*    Michael Luong #mcluong@ucsc.edu
*    Sudeep Baniya #sucbaniy@ucsc.edu
*    /afs/cats.ucsc.edu/courses/cmps112-wm/usr/racket/bin/mzscheme -qr
*    $Id: sbi.scm,v 1.4 2018-04-11 16:31:36-07 - - $

* NAME
*    functions.pl - Airline Reservation System
* SYNOPSIS
*    functions.pl database.pl

* DESCRIPTION
*    Given a request to travel from one city to another, print out the flight
*    schedule. For each leg of the trip, print out the departure airport, airport, city
*    name, and time. 
*/


/* Prolog's Version of NOT */
not( X ) :- X, !, fail.
not( _ ).

/* Converts from DMS to Radian */
dms_radian( degmin( Degrees, Minutes ), Total ) :-
    Total is (Degrees + Minutes / 60) * pi / 180.

/* Given by the teacher */
haversine_radians( Latitude1, Longitude1, Latitude2, Longitude2, Distancetotal, FlightTime ) :-
    DLatitude is Latitude2 - Latitude1,
    DLongitude is Longitude2 - Longitude1,
    A is sin( DLatitude / 2 ) ** 2 + cos( Latitude1 ) * cos( Latitude2 ) * sin( DLongitude / 2 ) ** 2,
    Distance is 2 * atan2( sqrt( A ), sqrt( 1 - A )),
    Distancetotal is (3961 * Distance ),
    FlightTime is (Distancetotal / 500).

 

/* Calulates the total distance from Airport1 to Airport2 */
flight_totaldistance( A1, A2, FlightTime ) :-
    airport( A1, _, X1, Y1 ),
    airport( A2, _, X2, Y2 ),
        dms_radian( X1, Latitude1 ),
        dms_radian( Y1, Longitude1 ),
        dms_radian( X2, Latitude2 ),
        dms_radian( Y2, Longitude2 ),
    haversine_radians( Latitude1, Longitude1, Latitude2, Longitude2, _, FlightTime).

    
/* Calulates the total time from Airport1 to Airport2 */
flight_totaltime(A1, A2, Hour, Min) :-
    flight_totaldistance( A1, A2, FlightTime ),
    Final = FlightTime * 60,
    FinalMin is round(Final),
    Min is mod(FinalMin, 60),
    Hour is div(FinalMin, 60).


/* Checks if it is needed to add leading zeros when number is less than 10 or else keep the same when greater than 9  */ 
time_format(Min) :-
    Min < 10,
    write(0),
    write(Min).

time_format(Min) :-
    Min >= 10,
    write(Min).


/* Checks to see if there is a direct fiight from Airport1 to Airport2 */
check_direct(Home, Destination) :- flight(Home, Destination, _), !.


/* 
convert_hours( Hours, Minutes, HourAMin ) :-
    HourAMin is Hours + Minutes / 60.

convert_minutes( Hours, Minutes ) :-
    Minutes is Hours * 60.
*/

to_upper( Lower, Upper) :-
   atom_chars( Lower, Lowerlist),
   maplist( lower_upper, Lowerlist, Upperlist),
   atom_chars( Upper, Upperlist).

 
/* Gets the name of of the city with the abbrevration of the airport */
airport_getname(AP, Name) :-
    airport(AP, Name, _, _),
    to_upper(AP, AP_cap),
    write(AP_cap, Name).

    
 
/* Checks the flights times from Home to Destination */
check_flighttime(time(Hours, Minute1), time(Hours, Minute2)) :- 
!, 
Minute2 > Minute1.

check_flighttime(time(Hour1, _), time(Hour2, _)) :- 
Hour2 > Hour1.  
    

/* Calulates the arrival time from Airport1 to Airport2 */
calculate_arrivaltime(A1, A2, time(DepartHours, DepartMinutes), time(ArriveHours, ArriveMinutes)) :-
    flight_totaltime(A1, A2, Hour, Min),
    ArriveMinutes is mod(DepartMinutes + Min, 60),
    ArriveHours is (DepartHours + Hour + div(Min + DepartMinutes, 60)).
   

/* Calulates the departure time from Airport1 */    
calculate_departuretime(time(ArriveHours, ArriveMinutes), time(NextDepartH, NextDepartM)):-
    NextDepartM is mod(ArriveMinutes + 30, 60),
    NextDepartH is ArriveHours + div(ArriveMinutes + 30, 60).
     

/* Find paths from a departure airport to a destination airport following the rules given in the pdf */ 
find_path( Head, Destination, List, Times ) :-
    flight(Head, _, Departure),
    find_path( Head, Destination, Departure, [Head], List, Times ).

find_path( Head, Head, _, [Head], []).

find_path( Head, Destination, Departure, _, 
    [Head|List], [Departure|Times] ) :-
    flight( Head, Destination, Departure ),
    find_path(Destination, Destination, _, List, Times).

find_path( Head, Destination, Departure, Approach, [Head|List], [Departure|Times]) :-
    (check_direct(Head, Destination) -> flight(Head, Destination, Departure),
    find_path(Head, Destination, Departure, _, List, Times)
    ;
    flight( Head, Next, Departure ),
    flight( Next, NextFlight, Next_Departure),
    not( member( Next, Approach )),
    not( member( NextFlight, Approach)),
    calculate_arrivaltime(Head, Next, Departure, Arrival),
    calculate_departuretime(Arrival, NextFlight_Departure),
    (check_flighttime(NextFlight_Departure, Next_Departure) -> find_path( Next, Destination, Next_Departure, [Next|Approach], List, Times );
    fail)
    ).


/*Write the given list using a certain form given departs and arrives paired with times.*/
write_path( [_], [] ) :-
    nl.

write_path( [Base|[Hub|List]], [time(Hour1, Minute1)|Times] ) :-
    flight(Base,Hub,time(Hour1,Minute1)),
    calculate_arrivaltime(Base, Hub, time(Hour1, Minute1), time(ArriveHours, ArriveMinutes) ),
    write('depart '), write(Base), write('  '), airport_getname(Base, _), write(''), write(Hour1), write(':'),
    time_format(Minute1),nl,
    
    write('depart '), write(Hub), write('  '), airport_getname(Hub, _), write(''), write(ArriveHours), write(':'),
    time_format(ArriveMinutes),nl,

    write_path( [Hub|List], Times ).


/* Checks to see if the airports are the same*/
fly( Depart, Depart ) :-
    write( 'Error: The Flights Airports Are The Same! ' ),
    nl,
    !, fail.
    
       
/* Find the Path from Airport1 to Airport2 */    
fly(Head, Destination) :-
    find_path(Head, Destination, List, Times),
    !,nl,
    write_path(List, Times),
    true.
    
    
    
/* Tells User there is no path */
fly( Depart, Arrive ) :-
    write( 'Error: There Is No Path From: ' ), write(Depart),
    write( ' to '), write(Arrive), write( '!' ),
    !, fail.



/* Checks to see if Airport is in the database or not */
fly( _, _) :-
    write( 'Error: The Airports Given Does Not Exists In The Database' ), nl,
!, fail.


/* Checks if flight is during Twilight Zone */
fly( Depart, Arrive ) :-
    airport( Depart, _, _, _ ),
    airport( Arrive, _, _, _ ),
    write( 'Error: Your Flight Is Not Possible In The Twilight Zone.' ),
    !, fail.
