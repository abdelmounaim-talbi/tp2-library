
# UC-07 – Register a return

## Goal
Register a returned copy and close its loan.

## Actors
Primary actor: Librarian.
Supporting actor: Member.

## Preconditions
The member has a borrowed copy to return.

## Trigger
The member brings back a copy to the library.

## Main success scenario
1. The member returns the copy.
2. The librarian registers the returned copy.
3. The system closes the loan.
4. The system checks whether the return is late.
5. The system checks whether the book is reserved.
6. If the book is reserved, the system keeps the copy aside and notifies the member who reserved it.

## Alternative flows
6a. If the book is not reserved, no reservation action is required.

## Extensions
4a. If the return is late, the extension point "late return" is activated and the use case "Suspend the member" is executed.

## Postconditions
- The loan is closed.
- If the book is reserved, the copy is kept aside.
- The member who reserved the book is notified.
- If the return is late, the use case "Suspend the member" is activated.
