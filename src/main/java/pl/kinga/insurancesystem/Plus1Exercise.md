a) Czym sie rozni Lazy od Eager loading? Ktore relacje sa domyslnie Lazy?

        Lazy loading jest domyslnym sposobem pobierania danych z bazy, konkretne dane sa pobierane dopiero gdy faktycznie sa potrzebne,
        co daje lepszy performance bo przy wielu zapytaniach tych danych zbednych moze byc bardzo duzo.
        Eager loading to chciwe pobieranie danych wszystko podczas jednego zapytania nawet te dane o ktore uzytkownik nie pytal.
b) Co to problem N+1? Ile zapytan zobaczylam w logu przy GET /agents (przed naprawka)?

        problem n+1 to sytuacja gdy 1 zapytanie generyje n dodatkowych zapytan wynikajacych z relacji w bazie. W logu mialam 
        33 zapytania ale to za duzo podejrzewam ze zapisane tez byly te z tworzenia polis i agentow a nie samgo get.
c) Ile zapytan jest po naprawce z JOIN FETCH?
        
    po naprawie mamy tylko jedno zapytanie na get/agents
d) Czym sie rozni @EntityGraph od JOIN FETCH? Kiedy ktory?

        @EntityGraph to adnotacja mowiaca Springowi ktore realcje maja byc zaladowane w danym zapytaniu, mozemy uzywac gdy mamy derivered query i logika zapytan nie jesy skomplikowana
        Join Fetch uzywamy do bardziej skomplikowanych zapytan (wiele warunkow, sortowan), w zapytaniach ktrore potrzebuja relacji.
e) Dlaczego nie ustawiamy FetchType.EAGER na encji jako fix na N+1?

        Poniewaz bysmy za kazdym zapytaniem ladowali relacje nawet gdy ona nie jest potrzebna. Lepiej w zapytaniach ktore tego potrzebuja uzyc Join fetch.
f) Wklej przyklad jednego zapytania SQL z logu ktore pokazuje N+1 (te powtarzajace sie).

        Hibernate: 
    insert 
    into
        agent
        (email, first_name, last_name, id) 
    values
        (?, ?, ?, default)
    Hibernate:
    insert
    into
    agent
    (email, first_name, last_name, id)
    values
    (?, ?, ?, default)
    Hibernate:
    insert
    into
    agent
    (email, first_name, last_name, id)
    values
    (?, ?, ?, default)