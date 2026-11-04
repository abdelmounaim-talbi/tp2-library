package library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Copy> copies = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();

    // Ajouter un livre
    public void addBook(Book book) {
        books.add(book);
    }

    // Ajouter un exemplaire d'un livre existant
    public void addCopy(String isbn, String inventoryNumber) {
        Book book = findBook(isbn);
        if (book == null) {
            throw new IllegalArgumentException("Livre non trouvé avec l'ISBN : " + isbn);
        }
        copies.add(new Copy(inventoryNumber, book));
    }

    // Enregistrer un membre
    public void registerMember(Member member) {
        members.add(member);
    }

    // Emprunter un exemplaire
    public Loan lend(String memberNumber, String inventoryNumber, LocalDate today) {
        Member member = findMember(memberNumber);
        if (member == null) {
            throw new IllegalArgumentException("Membre inconnu : " + memberNumber);
        }

        Copy copy = findCopy(inventoryNumber);
        if (copy == null) {
            throw new IllegalArgumentException("Exemplaire inconnu : " + inventoryNumber);
        }

        if (!copy.isAvailable()) {
            throw new IllegalStateException("L'exemplaire n'est pas disponible.");
        }

        long activeLoans = loans.stream()
                .filter(l -> l.getMember().getNumber().equals(memberNumber) && l.isOpen())
                .count();

        if (activeLoans >= 5) {
            throw new IllegalStateException("Un membre ne peut pas avoir plus de 5 emprunts en cours.");
        }

        copy.setAvailable(false);
        Loan loan = new Loan(member, copy, today);
        loans.add(loan);
        return loan;
    }

    // Retourner un exemplaire
    public void returnCopy(String inventoryNumber, LocalDate today) {
        Copy copy = findCopy(inventoryNumber);
        if (copy == null) {
            throw new IllegalArgumentException("Exemplaire inconnu : " + inventoryNumber);
        }

        Loan activeLoan = loans.stream()
                .filter(l -> l.getCopy().getInventoryNumber().equals(inventoryNumber) && l.isOpen())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Aucun emprunt actif pour cet exemplaire."));

        activeLoan.setReturnDate(today);
        copy.setAvailable(true);
    }

    // Partie D : Recherche de livres par mot-clé dans le titre (ignore la casse)
    public List<Book> searchByTitle(String keyword) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    // Méthodes utilitaires de recherche interne
    public Book findBook(String isbn) {
        return books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    public Copy findCopy(String inventoryNumber) {
        return copies.stream().filter(c -> c.getInventoryNumber().equals(inventoryNumber)).findFirst().orElse(null);
    }

    public Member findMember(String number) {
        return members.stream().filter(m -> m.getNumber().equals(number)).findFirst().orElse(null);
    }
}