
# UC-07 – Register a return

## Goal
Register a returned copy and close its loan.

## Actors
Primary actor: Librarian.
Secondary actors: Member, Mail server.

## Preconditions
The librarian is authenticated.

## Trigger
A member presents a copy to return.

## Main success scenario
1. The librarian identifies the member.
2. The system displays the member's information and current loans.
3. The librarian scans the barcode of the copy.
4. The system identifies the copy and checks that it is on loan.
5. The system closes the loan.
6. The system checks whether the return is late.
7. The system checks whether the copy is reserved.
8. If the copy is reserved, the system keeps it aside and notifies the member by email.
9. If the copy is not reserved, the system marks it as available.
10. The system prints the receipt.

## Alternative flows
1a. The barcode cannot be read: the librarian enters the inventory number; the use case continues at step 4.

## Exceptions
2a. The copy is not on loan: the system displays an error message and the use case ends.

*a. At any time, if the database connection is lost, the system displays an error.

## Postconditions
- The loan is closed.
- The copy is marked as available if it is not reserved.
- If reserved, the copy is kept aside and the member is notified.
- A receipt is printed.
