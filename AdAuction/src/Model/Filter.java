package Model;

import java.util.Date;

public class Filter {

    Date dataUpperRange;
    Date dataLowerRange;
    String gender;
    String age;
    String income;
    Context context;
    Boolean dataRangeSelected = false;
    Boolean genderSelected = false;
    Boolean ageSelected = false;
    Boolean incomeSelected = false;
    Boolean contextSelected = false;

    enum Context {
        News,
        Blog,
        Hobbies,
        Shopping,
        SocialMedia,
        Travel
    }




}
