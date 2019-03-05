package Model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Filter {

    private Date dateUpperRange = null;
    private Date dateLowerRange = null;
    private String gender = null;
    private String age = null;
    private String income = null;
    private Context context = null;
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

    public Map<String, String> getFilterArray() {
        Map<String, String> map = new HashMap<String, String>();
        if (dateRangeSelected){
            map.put("dateRange" , getDateLowerRange() + "+" + getDateUpperRange());
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
            map.put("context", String.valueOf(getContext()));
        }return map;
    }

    public Context getContext() {
        return context;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setContext(Context context) {
        this.context = context;
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
        this.gender = gender;
    }

    public void setGenderSelected(Boolean genderSelected) {
        this.genderSelected = genderSelected;
    }

    public void setIncome(String income) {
        this.income = income;
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

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getIncome() {
        return income;
    }


}