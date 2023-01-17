package pl.jbiesek.MoviesDB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jbiesek.MoviesDB.Entities.*;

import java.sql.Date;

@RestController
public class IndexController {

    @GetMapping("/test")
    public String test(){
        return "Hello world!";
    }

    @Autowired
    private ActorController actorController;

    @Autowired
    private ActorMovieController actorMovieController;

    @Autowired
    private ActorReviewController actorReviewController;

    @Autowired
    private DirectorController directorController;

    @Autowired
    private DirectorReviewController directorReviewController;

    @Autowired
    private MovieController movieController;

    @Autowired
    private MovieReviewController movieReviewController;

    @Autowired
    private UserController userController;

    @PostMapping("/generateModel")
    public String generateModel() {
        Date date_now = new Date(System.currentTimeMillis());
        // password : Abcd123!
        User u0 = new User("admin", "c41ec2ce741bfe8b2860989a94eb8c0b0bd7e3571022cbf4ee620a943d2de736f351162e29e1d8d99f15157e0a2c1e155364501f9e46a5d1ad139bdbb376d6d2", date_now);
        // password : Qwerty123!
        User u1 = new User("jbiesek", "6866cba5162384d96a3dbfae5817371a12485ae04e9f503552b1f4a4f222308057f163e002e3896bfa67b2a16db0f6496b52898cd1e68982543a1afb87a0c376", date_now);
        // password : M!siek321
        User u2 = new User("Ania2000", "38a466f2586a110a1cb4d6cf1363c71281e298356be05884c294dd0a58cfa996a8b3ca38d9264fc758b1c4fc1b5283d98f867a1e9296bfbee23bce0af52cada5", date_now);
        // password : Jan3kPL!
        User u3 = new User("JanekPL", "42204e42571e633970f608cd3f63e8f7b5a5d5d89ed8099bb3a6b46f7d86801c806b74598e41864add592b35d5b5933d8370a7bcecd61fb443b1ffbdf5a6825d", date_now);
        // password : Zuz!a1337
        User u4 = new User("zuzanna123", "09cf7f67d7645a0f7d5b64121cd84cea5a36ea88f96000eaebdd01699a5e5795829f47cc9bca70a78b566d6c31c755570840d436eee255781a0d86c60eba3cdb", date_now);

        Actor a0 = new Actor("Robert", "De Niro", new Date(42, 8, 17), "USA");
        Actor a1 = new Actor("Ray", "Liotta", new Date(54, 12, 18),"USA");
        Actor a2 = new Actor("Joe", "Pesci", new Date(43, 2, 9), "USA");
        Actor a3 = new Actor("Al", "Pacino", new Date(40, 4, 25), "USA");
        Actor a4 = new Actor("Michelle", "Pfeiffer", new Date(58, 4, 29), "USA");
        Actor a5 = new Actor("Johnny", "Deep", new Date(63, 6, 9), "USA");
        Actor a6 = new Actor("Brad", "Pitt", new Date(63, 12, 18), "USA");
        Actor a7 = new Actor("Angelina", "Jolie", new Date(75,6,4), "USA");
        Actor a8 = new Actor("Morgan", "Freeman", new Date(37,6,1), "USA");
        Actor a9 = new Actor("Winona", "Ryder", new Date(71, 10, 29), "USA");
        Actor a10 = new Actor("Edward", "Norton", new Date(69, 8, 18), "USA");

        Director d0 = new Director("Martin", "Scorsese", new Date(42, 11, 17), "USA");
        Director d1 = new Director("Francis", "Ford Coppola", new Date(39, 4, 7), "USA");
        Director d2 = new Director("Brian", "De Palma", new Date(40, 9, 11), "USA");
        Director d3 = new Director("Mike", "Newell", new Date(42, 3, 28), "USA");
        Director d4 = new Director("Doug", "Liman", new Date(65, 7, 24), "USA");
        Director d5 = new Director("David", "Fincher", new Date(62, 8, 28), "USA");
        Director d6 = new Director("James", "Mangold", new Date(63, 12, 16), "USA");
        Director d7 = new Director("Tim", "Burton", new Date(58, 8, 25), "USA");

        Movie m0 = new Movie("Chłopcy z ferajny",1990,146,"USA","Kilkunastoletni Henry i Tommy DeVito trafiają pod opiekę potężnego gangstera. Obaj szybko uczą się panujących w mafii reguł.",d0);
        Movie m1 = new Movie("Ojciec chrzestny II", 1974, 200, "USA", "Rok 1917. Młody Vito Corleone stawia pierwsze kroki w mafijnym świecie Nowego Jorku. Ponad 40 lat później jego syn Michael walczy o interesy i dobro rodziny.", d1);
        Movie m2 = new Movie("Człowek z blizną", 1983, 170, "USA", "Rok 1980. Pochodzący z Kuby przestępca, Tony Montana, tworzy w Miami narkotykowe imperium. ", d2);
        Movie m3 = new Movie("Donnie Brasco", 1997, 127, "USA", "Film oparty na faktach. Agent FBI pod pseudonimem Donnie Brasco wnika w środowisko mafijne, by rozpracować je od środka.", d3);
        Movie m4 = new Movie("Mr. & Mrs. Smith", 2005, 120, "USA", "Para płatnych zabójców pracujących dla dwóch tajnych agencji wiedzie monotonne małżeńskie życie. Niespodziewanie dostają zlecenie na siebie nawzajem.", d4);
        Movie m5 = new Movie("Siedem", 1995, 127, "USA", "Dwóch policjantów stara się złapać seryjnego mordercę wybierającego swoje ofiary według specjalnego klucza - siedmiu grzechów głównych.", d5);
        Movie m6 = new Movie("Przerwana lekcja muzyki", 1999, 127, "USA", "Susanna poznaje Lisę w zakładzie psychiatrycznym. Od niej uczy się akceptacji siebie. ", d6);
        Movie m7 = new Movie("Podziemny krąg", 1999, 139, "USA", "Cierpiący na bezsenność mężczyzna poznaje gardzącego konsumpcyjnym stylem życia Tylera Durdena, który jest jego zupełnym przeciwieństwem. ", d5);
        Movie m8 = new Movie("Edward Nożycoręki", 1990, 105, "USA", "Delikatny chłopak-cyborg, który zamiast dłoni ma nożyce, nagle zostaje bez opieki swojego twórcy. W pobliskim miasteczku poznaje nastolatkę, a potem zakochuje się w niej.", d7);
        Movie m9 = new Movie("Kasyno", 1995, 178, "USA", "Las Vegas, rok 1973. Hazardzista i bukmacher, Sam \"Ace\" Rothstein, na polecenie mafii zostaje szefem jednego z największych kasyn w mieście. ", d0);

        ActorReview ar0 = new ActorReview("Jeden z wielkich. Wielu zarzuca mu, że ostatnio gra w gorszych filmach i podobne role - a co może zagrać 75 latek?",
                "Poza tym co ma robić? Czekać na śmierć? Niech sobie gra skoro kocha grę, bo zarobiony już jest więc raczej kasa nie jest tu najważniejsza. A wybitnych ról i tak zagrał wystarczająco dużo.",
                date_now, 8, a0, u1);
        ActorReview ar1 = new ActorReview("Jedno jest pewne", "on ma najlepszą filmografie ze wszystkich aktorów w historii kina, co drugi film to klasyk.",
                date_now, 9, a0, u3);
        ActorReview ar2 = new ActorReview("Powiedzmy sobie szczerze - doskonały aktor!", "Zgadzam się z wszystkimi, którzy uważają, że jest niedoceniany!",
                date_now, 8, a1, u2);
        ActorReview ar3 = new ActorReview("NIEDOCENIONY!", "Tego aktora \"odkryłam\" niedawno, ale jestem wprost nim zachwycona. W filmie \"Kontrola\" zagrał tak rewelacyjnie, że nie mam słów. Jestem oczarowana jego grą aktorską. W każdym filmie jest genialny.",
                date_now, 8, a1, u4);
        ActorReview ar4 = new ActorReview("Ma facet energię! Jeden z moich ulubionych aktorów!", "Tak samo przekonywujący jest w rolach i poważnych i komediowych, a to rzadkość",
                date_now, 9, a2, u0);
        ActorReview ar5 = new ActorReview("Tylko 1 oskar ? Skandal", "Jak taki geniusz, najlepszy aktor wszech-czasów mógł dostać tylko jednego oskara, dla mnie to skandal :)",
                date_now, 10, a3, u1);
        ActorReview ar6 = new ActorReview("Lubię De Niro, ale Al jest moim zdaniem bardziej uniwersalny, wszechstronny", "Doskonały aktor, co tu więcej mówić",
                date_now, 9, a3, u3);
        ActorReview ar7 = new ActorReview("Najpiękniejsza!", "lekroć oglądam z nią filmy, zawsze myślę o jednym: nie ma i nie było piękniejszej kobiety od niej! Zachwycająca uroda i wdzięk!",
                date_now, 8, a4, u2);
        ActorReview ar8 = new ActorReview("\"Złote\" pokolenie Hollywood - Depp, Pitt, Clooney, Cruise... świetnie wyglądający aktorzy grający w największych hitach",
                "Uwielbiam Johnnego za luz i naturalny sposób grania. Ciekawe jak długo jeszcze uda mu się utrzymać ten młodzieńczy styl, bo wierzyć się nie chce, że to już 55-letni pan...",
                date_now, 7, a5, u3);
        ActorReview ar9 = new ActorReview("Jeden z symboli Hollywood", "Dobrze wygląda, dobrze gra i oby tak było jak najdłużej",
                date_now, 8, a6, u4);
        ActorReview ar10 = new ActorReview("Jolie się kocha, albo nienawidzi.", "Ja ją uwielbiam, jest wręcz cudowna. Nawet gdy gra w beznadziejnych i kiczowatych produkcjach",
                date_now, 9, a7, u3);
        ActorReview ar11 = new ActorReview("Boję się, że mi z lodówki wyskoczy", "Serio, lubię gościa, ale odnoszę wrażenie, że jest wszędzie",
                date_now, 6, a8, u1);
        ActorReview ar12 = new ActorReview("dawno powinna mieć Oscara", "genialna aktorka",
                date_now, 9, a9, u4);
        ActorReview ar13 = new ActorReview("Więzień nienawiści, Lęk pierwotny, Fight Club...", "Ktoś mi wytłumaczy jak to możliwe że ten gość nie ma na swoim koncie Oscara?",
                date_now, 9, a10, u2);

        DirectorReview dr0 = new DirectorReview("Jeden z najwybitniejszych twórców kina", "Wiem, że Ameryki nie odkryłem, ale musiałem to napisać...",
                date_now, 9, d0, u0);
        DirectorReview dr1 = new DirectorReview("Scorsese", "Za Chłopców z ferajny i Kasyno - Oscary się ewidentnie należały",
                date_now, 8, d0, u1);
        DirectorReview dr2 = new DirectorReview("Chyba najdziwniejszy filmowy twórca, jakiego filmy mogłem oglądać.",
                "Nakręcił 3 arcydzieła, za które słusznie obsypano go Oscarami lub nominacjami (Ojciec chrzestny, Ojciec chrzestny II, Czas apokalipsy), po czym zaczął trzaskać serię przeciętniaków, o których szybko zapomniano i które nie są warte uwagi.",
                date_now, 6, d1, u2);
        DirectorReview dr3 = new DirectorReview("Jakim cudem facet dostał nominację do maliny za Człowieka z blizną?",
                "hollywood jest popieprzone", date_now, 9, d2, u3);
        DirectorReview dr4 = new DirectorReview("A Oscar?", "Zastanawia mnie, dlaczego ten gość nie ma żadnej nagrody typu Oscar, Złoty Glob et cetera?",
                date_now, 7, d2, u4);
        DirectorReview dr5 = new DirectorReview(" 13 reżyserowanych filmów", "I każdy z nich jest hitem. Co dowodzi na to, że facet ma oko do dobrych scenariuszów i zna się na swojej pracy. Super.",
                date_now, 8, d5, u3);

        MovieReview mr0 = new MovieReview("Jeden z najlepszych filmów gangsterskch", "Wciąga od początku, nie wiem, jak ktoś może się na tym filmie nudzić, jak tu non stop coś się dzieje? Daję 9 a nie 10 dlatego, że ogólnie nie lubię zdrajców, a takim był jakby nie było pierwowzór głównego bohatera",
                date_now, 9, m0, u0);
        MovieReview mr1 = new MovieReview("Ten film ma jedną zaletę.", "Brak wad.", date_now, 10, m0, u1);
        MovieReview mr2 = new MovieReview("Majstersztyk", "Na pewno najlepszy sequel w historii kina.", date_now, 10, m1, u2);
        MovieReview mr3 = new MovieReview("Torpeda", "Jest to jeden z niewielu filmów gdzie jest długi a nie nudzi.", date_now, 10, m1, u3);
        MovieReview mr4 = new MovieReview("Hmm.", "Dlaczego Pacino nie dostał za rolę Tony,ego cholernego Oscara ? Przecież to jego najlepsza rola.", date_now, 9, m2, u4);
        MovieReview mr5 = new MovieReview("Film bardzo dobry, tylko", " los głównych bohaterów mało ciekawy....", date_now, 8, m2, u1);
        MovieReview mr6 = new MovieReview("Miał ktoś podobne wrażenie?", "Słysząc głos Nicky'ego miałam wrażenie, że to Joe Pesci , i troche mi go brakowało filmie :D", date_now, 9, m3, u2);
        MovieReview mr7 = new MovieReview("Nie wiem ", "Nie wiem dlaczego ale zawsze oglądam ten film jak jest w TV. Niby mi się średnio podoba ale jednak cos mnie do tego filmu ciągnie:D", date_now, 7, m4, u3);
        MovieReview mr8 = new MovieReview("Skandal", "Skandalem jest to, że ten film nie dostał choćby nominacji do oscara za scenariusz.\n" +
                "Z drugiej strony od czasu przegrania \"Citizen Kane'a\" z \"How Green Was my Valley\" ta nagroda przestała mieć jakiekolwiek znaczenie.", date_now, 9, m5, u1);
        MovieReview mr9 = new MovieReview("Znakomite kino z najlepszych lat Hollywood", "Kto spodziewał się takiego zakończenia oglądając pierwszy raz?", date_now, 9, m5, u3);
        MovieReview mr10 = new MovieReview("Średni 5/10", "Ciężki do oglądania brałem go na dwa podejścia a i tak strasznie mi się dłużył jak dla mnie średni nic mnie w tym filmie jakoś szczególnie mnie nie ujęło",
                date_now, 5, m6, u4);
        MovieReview mr11 = new MovieReview("Winiona!", "Moim zdaniem Winiona powinna dostać nagrodę za role w tym filmie, Angelina J. też dobrze zagrała",
                date_now, 10, m6, u3);
        MovieReview mr12 = new MovieReview("Jeden z tych filmów, o których chce się zapomnieć...", "tylko dlatego, aby móc obejrzeć go znowu po raz pierwszy!", date_now, 9, m7, u2);
        MovieReview mr13 = new MovieReview("Arcydzieło o naszej brudnej naturze", "Przeszywający, miażdżący mózg. Brudny, mokry, autentyczny. Istne arcydzieło. Rzadko film na podstawie książki może z nią konkurować.",
                date_now, 10, m7, u1);
        MovieReview mr14 = new MovieReview("muzyka w filmie\n", "Film jest po prostu genialny ale to co najbardziej mnie urzekło to muzyka. Caly czas w tle leci muzyka jest ona tak genialnie wkomponowana i tak dobrze dopasowana, że po prostu brakuje słów.",
                date_now, 10, m9, u2);

        ActorMovie am0 = new ActorMovie(m0, a0, "James \"Jimmy\" Conway");
        ActorMovie am1 = new ActorMovie(m0, a1, "Henry Hill");
        ActorMovie am2 = new ActorMovie(m0, a2, "Tommy DeVito");
        ActorMovie am3 = new ActorMovie(m1, a3, "Michael Corleone");
        ActorMovie am4 = new ActorMovie(m1, a0, "Młody Vito Corleone");
        ActorMovie am5 = new ActorMovie(m2, a3, "Tony Montana");
        ActorMovie am6 = new ActorMovie(m2, a4, "Elvira Hancock");
        ActorMovie am7 = new ActorMovie(m3,a3, "Benjamin \"Lewus\" Ruggiero");
        ActorMovie am8 = new ActorMovie(m3, a5, "Donnie Brasco / Joseph D. \"Joe\" Pistone");
        ActorMovie am9 = new ActorMovie(m4, a6, "John Smith");
        ActorMovie am10 = new ActorMovie(m4, a7, "Jane Smith");
        ActorMovie am11 = new ActorMovie(m5, a6, "Mills");
        ActorMovie am12 = new ActorMovie(m5, a8, "Somerset");
        ActorMovie am13 = new ActorMovie(m6, a9, "Susanna Kaysen");
        ActorMovie am14 = new ActorMovie(m6, a7, "Lisa Rowe");
        ActorMovie am15 = new ActorMovie(m8, a5, "Edward Nożycoręki");
        ActorMovie am16 = new ActorMovie(m8, a9, "Kim Boggs");
        ActorMovie am17 = new ActorMovie(m9, a0, "Sam \"Ace\" Rothstein");
        ActorMovie am18 = new ActorMovie(m9, a2, "Nicky Santoro");
        ActorMovie am19 = new ActorMovie(m7, a10, "Narrator");
        ActorMovie am20 = new ActorMovie(m7, a6, "Tyler Durden");

        userController.add(u0);
        userController.add(u1);
        userController.add(u2);
        userController.add(u3);
        userController.add(u4);

        actorController.add(a0);
        actorController.add(a1);
        actorController.add(a2);
        actorController.add(a3);
        actorController.add(a4);
        actorController.add(a5);
        actorController.add(a6);
        actorController.add(a7);
        actorController.add(a8);
        actorController.add(a9);
        actorController.add(a10);

        directorController.add(d0);
        directorController.add(d1);
        directorController.add(d2);
        directorController.add(d3);
        directorController.add(d4);
        directorController.add(d5);
        directorController.add(d6);
        directorController.add(d7);

        movieController.add(m0);
        movieController.add(m1);
        movieController.add(m2);
        movieController.add(m3);
        movieController.add(m4);
        movieController.add(m5);
        movieController.add(m6);
        movieController.add(m7);
        movieController.add(m8);
        movieController.add(m9);

        actorReviewController.add(ar0);
        actorReviewController.add(ar1);
        actorReviewController.add(ar2);
        actorReviewController.add(ar3);
        actorReviewController.add(ar4);
        actorReviewController.add(ar5);
        actorReviewController.add(ar6);
        actorReviewController.add(ar7);
        actorReviewController.add(ar8);
        actorReviewController.add(ar9);
        actorReviewController.add(ar10);
        actorReviewController.add(ar11);
        actorReviewController.add(ar12);
        actorReviewController.add(ar13);

        directorReviewController.add(dr0);
        directorReviewController.add(dr1);
        directorReviewController.add(dr2);
        directorReviewController.add(dr3);
        directorReviewController.add(dr4);
        directorReviewController.add(dr5);

        movieReviewController.add(mr0);
        movieReviewController.add(mr1);
        movieReviewController.add(mr2);
        movieReviewController.add(mr3);
        movieReviewController.add(mr4);
        movieReviewController.add(mr5);
        movieReviewController.add(mr6);
        movieReviewController.add(mr7);
        movieReviewController.add(mr8);
        movieReviewController.add(mr9);
        movieReviewController.add(mr10);
        movieReviewController.add(mr11);
        movieReviewController.add(mr12);
        movieReviewController.add(mr13);
        movieReviewController.add(mr14);

        actorMovieController.add(am0);
        actorMovieController.add(am1);
        actorMovieController.add(am2);
        actorMovieController.add(am3);
        actorMovieController.add(am4);
        actorMovieController.add(am5);
        actorMovieController.add(am6);
        actorMovieController.add(am7);
        actorMovieController.add(am8);
        actorMovieController.add(am9);
        actorMovieController.add(am10);
        actorMovieController.add(am11);
        actorMovieController.add(am12);
        actorMovieController.add(am13);
        actorMovieController.add(am14);
        actorMovieController.add(am15);
        actorMovieController.add(am16);
        actorMovieController.add(am17);
        actorMovieController.add(am18);
        actorMovieController.add(am19);
        actorMovieController.add(am20);

        return "Model generated!";
    }
}
