a) Jaka relacja laczy Customer z Agent i dlaczego? Kto jest "owning side"?

        Customer z Agent to relacja Many to One -> jeden Agent moze miec wiele klientow
        Owning side -> Customer
b) Jaka relacja laczy Customer z Policy? Kto jest "owning side" i dlaczego?

        Cutomer z Policy jest polaczony relcja One To Many jeden klient moze miec wiele polis.
        Owning side -> Policy.
c) Dlaczego przy przypisywaniu polisy do klienta ustawiasz policy.setCustomer(customer)
a nie customer.getPolicies().add(policy)?

        ustawilam te dwie rzeczy 
               customer.getPolicies().add(policy);
        policy.setCustomer(customer);
        Wyjaśniam:
        policy.setCustomer(customer) jest konieczne (bo to owning side — bez tego Hibernate nie zapisze FK). customer.getPolicies().add(policy)
        jest dobre dla spójności w pamięci, ale nie konieczne dla bazy. Robiąc obie rzeczy, masz pewność, że obiekt w Javie i rekord w bazie są
        zsynchronizowane
d) Wymien 3 rzeczy z modulu 12, ktore Twoim zdaniem sa najwazniejsze na rozmowie rekrutacyjnej.

        Najwazniejsze rzeczy do zapytania:
            - relacje pomiedzy encjami (OneToMany itd) i ich reprezentacje w bazie danych
            - rozroznienie lazy od eager loading i jak naprawic N+1 problem,
            - pliki migracyjne
            