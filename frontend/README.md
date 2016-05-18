# Odpalanie projektu

1. Trzeba mieć zainstalowanego node'a.

2. W konsoli wchodzimy do folderu Webcrawler/frontend

3. Wpisujemy npm install.

4. Wpisujemy npm start.

5. Serwer powinien nasłuchiwać na localhost:8080

6. Jeśli coś nie działa, pod Windowsem można spróbować wpisać npm install -g npm

7. Jeśli nie działa pod Linuksem, trzeba kombinować albo poczekać aż ktoś tu napisze rozwiązanie.

#Interfejs frontend-backend



####Dodawanie nowych wyszukań:

    //metoda: POST
    //url: 'serach?token=TOKEN_USERA'
    //dane IN:
    {
        keyword: SZUKANA_FRAZA
    }

####Wyświetlanie listy wyszukań dla danego usera:

    //metoda: GET
    //url: 'serach?token=TOKEN_USERA&page=NUMER_STRONY
    //dane OUT:
    {
        searches: [
            {
                id: 'xxxxxx',
                keyword: 'Lech Wałęsa',
                addingDate: 1463496202011, //data w postaci ilości milisekund od roku 1970
                howManyResults: 15, //ilość wyników znalezionych w ramach tego wyszukania
                crawlingTime: 3.22 //czas wyszkiwania
            },{
                id: 'xxxx23',
                keyword: 'Jan Kowalski',
                addingDate: 1463496202011,
                howManyResults: 15,
                crawlingTime: 1.334
            },{
                id: 'xxx23423xx',
                keyword: 'Juwenalia 2017',
                addingDate: 1463496202011,
                howManyResults: 15,
                crawlingTime: 0.012
            }
        ],
        pages: 30 //ilość stron, które zajmują wszystkie wyszukania (przyjąć jakąś stałą liczbę wpisów na stronę)
    }

####Wyświetlanie rezultatów danego wyszukania:

    //metoda: GET
    //url: 'serach?token=TOKEN_USERA&id=ID_WYSZUKANIA&page=NUMER_STRONY
    //dane OUT:
    {
        search: {
            id: 'xxxxxx',
            keyword: 'Lech Wałęsa',
            addingDate: 1463496202011,
            howManyResults: 15,
            crawlingTime: 3.22
        },
        results: [
            {
                id: 'xxxxxx',
                url: 'http://google.com',
                repeats: 12         //ile razy fraza powtórzyła się na tej stronie
            },{
                id: 'xxxx23',
                url: 'http://facebook.com',
                repeats: 23
            }
        ],
        pages: 30 //ilość wszystkich stron j.w.
    }
