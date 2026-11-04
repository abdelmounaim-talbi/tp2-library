# Git steps for TP 2 (copy-paste guide)

## Part A – configuration (once)
```
git config --global user.name "Your Name"
git config --global user.email "you@example.org"
git config --global init.defaultBranch main
```

## Part B/C – first commits (run in the tp2-library folder)
```
git init
git add pom.xml .gitignore README.md
git commit -m "Create the Maven project"

git add src/main/java/library/Book.java src/main/java/library/Copy.java
git commit -m "Add Book and Copy classes"

git add src/main/java/library/Member.java src/main/java/library/Loan.java
git commit -m "Add Member and Loan classes"

git add src/main/java/library/Library.java
git commit -m "Add Library with lend and return rules"

git add src/test
git commit -m "Add LibraryTest for the lending rules"

git log --oneline
```

## Part D – branch, merge, conflict
```
git switch -c feature/search
```
Add this method to `Library.java` (and `import java.util.List;` is already there):
```java
    public List<Book> searchByTitle(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }
```
Add this test to `LibraryTest.java`:
```java
    @Test
    void searchByTitleIgnoresCase() {
        assertEquals(1, library.searchByTitle("software").size());
        assertEquals(0, library.searchByTitle("cooking").size());
    }
```
Change the first line of README.md to `# Library management – TP 2 (with search)`, then:
```
git add .
git commit -m "Add searchByTitle"

git switch main
```
Change the first line of README.md to `# Library management application – TP 2`, then:
```
git add README.md
git commit -m "Rename the application in the README"

git merge feature/search
```
Fix the conflict in README.md (keep one line, delete the `<<<<<<<`, `=======`, `>>>>>>>` markers):
```
git add README.md
git commit
git log --oneline --graph --all
git branch -d feature/search
```

## Part E – remote repository
```
git remote add origin <URL of the empty repository>
git push -u origin main
git tag -a v1.0 -m "First version"
git push origin v1.0
```
