package library;

import java.time.LocalDate;

public class Loan {
    private Member member;
    private Copy copy;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Loan(Member member, Copy copy, LocalDate startDate) {
        this.member = member;
        this.copy = copy;
        this.startDate = startDate;
        this.dueDate = startDate.plusDays(14); // Emprunt pour 14 jours
        this.returnDate = null;
    }

    public Member getMember() {
        return member;
    }

    public Copy getCopy() {
        return copy;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    // Un emprunt est ouvert s'il n'a pas encore été rendu
    public boolean isOpen() {
        return returnDate == null;
    }

    // Vérifie si l'emprunt est en retard par rapport à une date donnée
    public boolean isLate(LocalDate today) {
        if (isOpen()) {
            return today.isAfter(dueDate);
        }
        return returnDate.isAfter(dueDate);
    }
}