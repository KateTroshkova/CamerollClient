package presenter;

import data.Cinema;
import data.Hall;
import data.Movie;
import data.Session;

import java.util.ArrayList;
import java.util.HashSet;

public class PreviewScenePresenter extends BasePresenter<IMVPContract.IPreviewScene> {

    private Movie avengers=new Movie("Мстители: Финал", "Оставшиеся в живых члены команды Мстителей и их союзники должны разработать новый план, который поможет противостоять разрушительным действиям могущественного титана Таноса. После наиболее масштабной и трагической битвы в истории они не могут допустить ошибку.",
            "Роберт Дауни мл, Крис Эванс, Марк Руффало, Крис Хемсворт, Скарлетт Йоханссон, Джереми Реннер, Дон Чидл, Пол Радд Бенедикт Камбербэтч",
            "фантастика, фэнтези, боевик, приключения", "США");

    private Movie jon = new Movie("Джон Уик 3", "Суперкиллер Джон Уик после нарушения кодекса тайной гильдии ассасинов получает статус изгоя — экскомьюникадо. За его голову назначена цена в 14 миллионов долларов, и армия самых жестоких профессиональных убийц со всего мира открывает на него кровавую охоту.",
            "Киану Ривз, Азия Диллон, Джером Флинн, Иэн МакШейн, Хироюки Санада, Холли Берри, Робин Лорд Тейлор, Лэнс Реддик, Лоренс Фишбёрн",
            "боевик, триллер", "США");
    private Movie genius = new Movie("Игры разумов", "Англия середины XIX века. Оксфордский профессор Джеймс Мюррей работает над первым в истории словарем английского языка, а его главным соратником становится заключенный психиатрической клиники для особо опасных преступников — доктор Уильям Майнор. Гений Майнор или сумасшедший, хитроумный преступник, ведущий дерзкую игру, чтобы вырваться на свободу, или одержимый ученый? И кто тогда Мюррей: верный друг, жертва манипуляции или организатор сговора?",
            "Мэл Гибсон, Шон Пенн, Натали Дормер, Джереми Ирвин, Дженнифер Эль, Стивен Диллэйн, Эдди Марсан, Стив Куган, Йоан Гриффит",
            "триллер, драма, детектив, биография", "Ирладния");
    private Movie joker = new Movie("Джокер", "Готэм, начало 1980-х годов. Комик Артур Флек живет с больной матерью, которая с детства учит его «ходить с улыбкой». Пытаясь нести в мир хорошее и дарить людям радость, Артур сталкивается с человеческой жестокостью и постепенно приходит к выводу, что этот мир получит от него не добрую улыбку, а ухмылку злодея Джокера.",
            "Хоакин Феникс, Роберт Де Ниро, Зази Битц, Брайан Коллен, Шей Уигэм, Брайан Тайри Генри, Гленн Флешлер, Фрэнсис Конрой, Дуглас Ходж",
            "триллер, драма, криминал", "США");
    private Movie lion = new Movie("Король Лев", "Киноверсия анимационной классики «Диснея» об обитателях африканской саванны, которым не хуже людей знакома любовь и дружба, вероломство и борьба за власть. У царственного льва Муфасы рождается наследник Симба, которому уготован извилистый путь от избалованного непоседы до несчастного изгоя, от беспечного бродяги до спасителя и повелителя всех зверей.",
            "Сет Роген, Дональд Гловер, Чиветель Эджиофор, Бейонсе, Джеймс Эрл Джонс, Кигэн-Майкл Ки, Эми Седарис, Элфри Вудард, Билли Айкнер",
            "мультфильм, мюзикл, драма, приключения, семейный", "США");
    private Movie king = new Movie("Рождённый стать королём", "Повседневные проблемы 12-летнего Алекса отходят на второй план после того, как он находит легендарный Экскалибур. И теперь, когда меч короля Артура оказывается в руках самого неудачливого подростка в Британии, начинается грандиозное приключение, в ходе которого Алекс и его друзья должны остановить злую средневековую волшебницу Моргану и не дать ей уничтожить мир.",
            "Луис Эшборн Серкис, Денис Гоф, Дин Чауму, Том Тейлор, Рианна Доррис, Нэйтан Стюарт-Джарретт, Нома Думезвени, Ребекка Фергюсон, Марк Боннар",
            "фэнтези, боевик, приключения, семейный", "Великобритания");

    private Cinema avrora=new Cinema("Аврора", "пр. Карла Маркса, 49, Новосибирск");
    private Cinema park=new Cinema("Синема парк", " пл. Карла Маркса, 7, 3-й этаж, Новосибирск");
    private Cinema victory = new Cinema("Победа", "ул. Ленина, 7, Новосибирск");
    private Cinema kino = new Cinema("Киносити", "ул. Фрунзе, 238, Новосибирск");

    private Movie loadMovie=null;
    private Cinema loadCinema=null;

    private String pattern1="8;10;0;0;0;0;0;0;0;0;-2;0;0;0;0;0;0;0;0;0;-2;0;0;0;0;0;0;0;0;0;-2;0;0;0;0;0;0;0;0;0;-2;0;0;0;0;-2;-2;0;0;0;-2;0;0;0;0;-2;-2;0;0;0;-2;0;0;0;0;-2;-2;0;0;0;-2;0;0;0;0;0;0;0;0;0;-2;0;0;0;0;0;0;0;0;0;-2;0";
    private String pattern2="6;6;-2;-2;-2;-2;0;0;0;-2;-2;-2;0;0;0;-2;0;0;0;0;0;-2;0;0;0;0;0;-2;0;0;0;0;0;0;0;0;0;0";
    private String pattern3="9;7;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;-2;-2;0;0;0;0;0;-2;-2;0;0;0;0;0;-2;-2;0;0;0;0;0;0;-2;0;0;0;0;0;0;-2;0;0;0;0;0;0;-2;0;0;0;0;0;0;-2";
    private String pattern4="8;6;0;0;0;0;0;0;0;0;0;0;0;0;0;0;-2;-2;0;0;0;0;-2;-2;0;0;0;0;-2;-2;0;0;0;0;-2;-2;0;0;0;0;-2;-2;0;0;0;0;-2;-2;0;0";
    private Hall hall1=new Hall();
    private Hall hall2=new Hall();
    private Hall hall3=new Hall();
    private Hall hall4=new Hall();
    private Hall hall5=new Hall();
    private Hall hall6=new Hall();
    private Hall hall7=new Hall();
    private Hall hall8=new Hall();

    Session sessions[];


    @Override
    public void viewIsReady() {
        HashSet<String> uniqueMovie=new HashSet<>();
        HashSet<String> uniqueCinema=new HashSet<>();
        HashSet<String> uniqueDate=new HashSet<>();
        HashSet<String> uniqueTime=new HashSet<>();
        hall1.setPlacePattern(pattern1);
        hall1.setName("Зал 1");
        hall2.setPlacePattern(pattern2);
        hall2.setName("Зал 2");
        hall3.setPlacePattern(pattern3);
        hall3.setName("Зал 3");
        hall4.setPlacePattern(pattern4);
        hall4.setName("Зал 4");
        hall5.setPlacePattern(pattern1);
        hall5.setName("Зал 5");
        hall6.setPlacePattern(pattern2);
        hall6.setName("Зал 6");
        hall7.setPlacePattern(pattern3);
        hall7.setName("Зал 7");
        hall8.setPlacePattern(pattern4);
        hall8.setName("Зал 8");
        sessions=new Session[20];
        sessions[0]=new Session(avengers, avrora, hall1, "12.00", "29.04.2019", 400);
        sessions[1]=new Session(avengers, park, hall2, "12.00", "29.04.2019", 300);
        sessions[2]=new Session(avengers, park, hall5, "15.00", "29.04.2019", 300);
        sessions[3]=new Session(avengers, park, hall3, "18.00", "29.04.2019", 300);
        sessions[4]=new Session(avengers, victory, hall4, "14.00", "1.05.2019", 200);
        sessions[5]=new Session(avengers, kino, hall6, "10.00", "05.05.2019", 200);
        sessions[6]=new Session(avengers, kino, hall7, "22.00", "06.05.2019", 300);
        sessions[7]=new Session(genius, victory, hall1, "12.00", "01.05.2019", 400);
        sessions[8]=new Session(genius, victory, hall2, "14.00", "02.05.2019", 300);
        sessions[9]=new Session(genius, avrora, hall8, "16.00", "03.05.2019", 500);
        sessions[10]=new Session(jon, park, hall3, "12.00", "30.06.2019", 400);
        sessions[11]=new Session(jon, avrora, hall1, "18.00", "30.06.2019", 400);
        sessions[12]=new Session(joker, kino, hall3, "12.00", "15.10.2019", 480);
        sessions[13]=new Session(joker, park, hall4, "12.00", "15.10.2019", 480);
        sessions[14]=new Session(joker, avrora, hall5, "12.00", "29.04.2019", 400);
        sessions[15]=new Session(lion, avrora, hall2, "18.00", "10.08.2019", 250);
        sessions[16]=new Session(lion, park, hall8, "19.00", "10.08.2019", 250);
        sessions[17]=new Session(lion, kino, hall7, "20.00", "10.08.2019", 250);
        sessions[18]=new Session(king, victory, hall6, "10.00", "15.07.2019", 300);
        sessions[19]=new Session(king, victory, hall8, "10.00", "15.07.2019", 300);

        ArrayList<Session> result=new ArrayList<>();
        for(Session session:sessions){
            if (loadMovie!=null) {
                if (session.getMovie().equals(loadMovie)){
                    result.add(session);
                }
            }
            if (loadCinema!=null){
                if (session.getCinema().equals(loadCinema)){
                    result.add(session);
                }
            }
        }
        Session[] r=new Session[result.size()];
        for(int i=0; i<result.size(); i++){
            r[i]=result.get(i);
        }
        for(Session s:result){
            uniqueMovie.add(s.getMovie().getName());
            uniqueCinema.add(s.getCinema().getName());
            uniqueDate.add(s.getDate());
            uniqueTime.add(s.getTime());
        }
        getView().onSessionDataReady(r);
        getView().setMovies(uniqueMovie);
        getView().setCinemas(uniqueCinema);
        getView().setDates(uniqueDate);
        getView().setTimes(uniqueTime);
    }

    public void alertMovie(Movie movie){
        this.loadMovie=movie;
    }

    public void alertCinema(Cinema cinema){
        this.loadCinema=cinema;
    }

    public void onFilterClick(String movie, String cinema, String hall, String time, String date){
        ArrayList<Session> data=new ArrayList<>();
        for (Session session:sessions){
            if ((movie.isEmpty() ||session.getMovie().getName().equals(movie)) &&
                    (cinema.isEmpty() ||session.getCinema().getName().equals(cinema)) &&
                    (hall.isEmpty() ||session.getHall().getName().equals(hall)) &&
                    (time.isEmpty() ||session.getTime().equals(time)) &&
                    (date.isEmpty() ||session.getDate().equals(date))){
                data.add(session);
            }
        }
        Session[] res=new Session[data.size()];
        for (int i=0; i<data.size(); i++){
            res[i]=data.get(i);
        }
        HashSet<String> uniqueMovie=new HashSet<>();
        HashSet<String> uniqueCinema=new HashSet<>();
        HashSet<String> uniqueDate=new HashSet<>();
        HashSet<String> uniqueTime=new HashSet<>();
        for(Session s:res){
            uniqueMovie.add(s.getMovie().getName());
            uniqueCinema.add(s.getCinema().getName());
            uniqueDate.add(s.getDate());
            uniqueTime.add(s.getTime());
        }
        getView().onSessionDataReady(res);
        getView().setMovies(uniqueMovie);
        getView().setCinemas(uniqueCinema);
        getView().setDates(uniqueDate);
        getView().setTimes(uniqueTime);
    }

    public void signInClick(){

    }

    public void signUpClick(){

    }
}
