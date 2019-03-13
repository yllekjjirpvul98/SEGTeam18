package Model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Filter {

    private Date dateUpperRange = null;
    private Date dateLowerRange = null;
    private ArrayList<String> gender = new ArrayList<String>();
    private ArrayList<String> age = new ArrayList<String>();
    private ArrayList<String> income = new ArrayList<String>();
    private ArrayList<Context> context = new ArrayList<Context>();
    private Boolean dateRangeSelected = false;
    private Boolean genderSelected = false;
    private Boolean ageSelected = false;
    private Boolean incomeSelected = false;
    private Boolean contextSelected = false;

    Filter(boolean dateRangeSelected, boolean genderSelected, boolean ageSelected, boolean incomeSelected, boolean contextSelected){
        this.dateRangeSelected = dateRangeSelected;
        this.genderSelected = genderSelected;
        this.ageSelected = ageSelected;
        this.incomeSelected = incomeSelected;
        this.contextSelected = contextSelected;
    }

    public enum Context {
        News,
        Blog,
        Hobbies,
        Shopping,
        SocialMedia,
        Travel
    }

    public Map<String, ArrayList<String>> getFilterArray() {
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        if (dateRangeSelected){
            ArrayList<String> date = new ArrayList<String>();
            date.add(getDateLowerRange() + "+" + getDateUpperRange());
            map.put("dateRange" , date);
        }
        if (genderSelected){
            map.put("gender", getGender());
        }
        if (ageSelected){
            map.put("age", getAge());
        }
        if (incomeSelected){
            map.put("income", getIncome());
        }
        if (contextSelected) {
            ArrayList<String> c = new ArrayList<String>();
            for(Context ct : context){
                c.add(String.valueOf(ct));
            }
            map.put("context", c);
        }return map;
    }

    public ArrayList<Context> getContext() {
        return context;
    }

    public void setAge(String age) {

        this.age.add(age);
    }

    public void setContext(Context context) {
        this.context.add(context);
    }

    public Boolean getAgeSelected() {
        return ageSelected;
    }

    public void setAgeSelected(Boolean ageSelected) {
        this.ageSelected = ageSelected;
    }

    public void setContextSelected(Boolean contextSelected) {
        this.contextSelected = contextSelected;
    }

    public void setdateLowerRange(Date dateLowerRange) {
        this.dateLowerRange = dateLowerRange;
    }

    public void setDateRangeSelected(Boolean dateRangeSelected) {
        this.dateRangeSelected = dateRangeSelected;
    }

    public void setDateUpperRange(Date dateUpperRange) {
        this.dateUpperRange = dateUpperRange;
    }

    public void setGender(String gender) {
        this.gender.add(gender);
    }

    public void setGenderSelected(Boolean genderSelected) {
        this.genderSelected = genderSelected;
    }

    public void setIncome(String income) {
        this.income.add(income);
    }

    public void setIncomeSelected(Boolean incomeSelected) {
        this.incomeSelected = incomeSelected;
    }

    public Boolean getContextSelected() {
        return contextSelected;
    }

    public Boolean getDateRangeSelected() {
        return dateRangeSelected;
    }

    public Boolean getGenderSelected() {
        return genderSelected;
    }

    public Boolean getIncomeSelected() {
        return incomeSelected;
    }

    public String getDateLowerRange() {
        return dateLowerRange.toString();
    }

    public String getDateUpperRange() {
        return dateUpperRange.toString();
    }

    public ArrayList<String> getAge() {
        return age;
    }

    public ArrayList<String> getGender() {
        return gender;
    }

    public ArrayList<String> getIncome() {
        return income;
    }


}
