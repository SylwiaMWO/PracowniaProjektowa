# PracowniaProjektowa
Wstęp

Stworzyliśmy dla Państwa wygodne i funkcjonalne oprogramowanie, które ułatwi przygotowanie kilku rodzajów raportów. Aplikacja ma pomóc Państwu w codziennej pracy, aby zaoszczędzić czas i sprawić, by żmudna i pracochłonna praca nie była jedynie przykrym obowiązkiem.
Niniejsza instrukcja ma na celu wprowadzenie w naszą aplikację oraz omówienie jej wszystkich funkcjonalności. Z pewnością pomoże Państwu w obsłudze naszego oprogramowania. 

Cel aplikacji 
Celem aplikacji jest przygotowanie raportów do analiz na podstawie danych wejściowych dotyczących wskazanej pracochłonności. 

Uruchomienie aplikacji – wymagania wstępne 
W celu uruchomienia aplikacji wymagane jest zainstalowane środowisko uruchomieniowe języka Java w wersji 14 lub nowszej. 

Rodzaje raportów 
Aplikacja umożliwia przygotowanie 3 typów raportów: 
1. Raport 1 – Projekt vs godziny
2. Raport 2 – Osoba vs godziny/projekt (w trakcie przygotowania) 
2. Raport 3 – Zadanie vs suma godzin/zadanie

Przygotowanie danych wejściowych 
Dane wejściowe należy przygotować w formie pliku .xlsx dla każdego pracownika, który realizował zadania projektowe w zadanym okresie. 
Nazwa pliku to „nazwisko_imię.xlsx” 
Plik z danymi powinien zawierać 3 kolumny: 
- data
- zadanie
- czas w h
Jeśli dane dotyczą różnych projektów wówczas te dane powinny znaleźć się w osobnych zakładkach. 

Interfejs aplikacji 
W aplikacji została zaimplementowana metoda, która w zadanym folderze szuka plików o rozszerzeniu .xlsx. Po odnalezieniu plików zapisuje je do listy. 
Za pomocą komendy „path” można zmienić folder, w którym znajdują się pliki z danymi wejściowymi. 

Budowanie aplikacji 
Budowanie aplikacji odbywa się za pomocą komendy: 
Build App: mvn clean compile assembly:single

Uruchamianie aplikacji 
Uruchamianie aplikacji odbywa się za pomocą komendy:
Run App: java -jar target/PracowniaProjektowa-1.0-SNAPSHOT-jar-with-dependencies.jar [Arguments, for example -path ./Data/reporter-dane/reporter-dane] 

Generowanie raportu 1 
Aby wygenerować raport 1 należy w linii komend dopisać -report_1. 

Generowanie raportu 3
Aby wygenerować raport 3 należy w linii komend dopisać -report_3 

Zapisywanie raportu do pliku do xlsx
Przy wywoływaniu komendy należy podać ścieżkę, gdzie zapisze się wygenerowany raport. 
Zapisywanie raportu do pliku xlsx odbywa się automatycznie.

Typy błędów w przypadku braku argumentów 
W przypadku braku poszczególnych argumentów w konsoli pojawi się komunikat o błędzie. 
1. „Musisz podać ścieżkę. Zrób to za pomocą komendy ‘-path’.”
2. „Musisz podać ścieżkę, gdzie zapisać plik. Zrób to za pomocą komendy ‘-output’.”
3. „Musisz podać, co zrobić z danymi. Wykorzystaj opcję raportowania.”
4. „Nie możesz użyć pustej ścieżki.”

Przykład uruchamiania:
java -jar target/PracowniaProjektowa-1.0-SNAPSHOT-jar-with-dependencies.jar -path  /home/Documents/Data -report_1 -output /home/Documents/Data/Output
