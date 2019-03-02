package Model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Filter {

    Date dateUpperRange = null;
    Date dateLowerRange = null;
    String gender = null;
    String age = null;
    String income = null;
    Context context = null;
    Boolean dateRangeSelected = false;
    Boolean genderSelected = false;
    Boolean ageSelected = false;
    Boolean incomeSelected = false;
    Boolean contextSelected = false;

    Filter(boolean dateRangeSelected, boolean genderSelected, boolean ageSelected, boolean incomeSelected, boolean contextSelected){
        this.dateRangeSelected = dateRangeSelected;
        this.genderSelected = genderSelected;
        this.ageSelected = ageSelected;
        this.incomeSelected = incomeSelected;
        this.contextSelected = contextSelected;
    }

    enum Context {
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
            map.put("dateRange" , getDateUpperRange() + "+" + getDateUpperRange());
        }else if (genderSelected){
            map.put("gender", getGender());
        }else if (ageSelected){
            map.put("age", getAge());
        }else if (incomeSelected){
            map.put("income", getIncome());
        }else if (contextSelected) {
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

    public Date getDateLowerRange() {
        return dateLowerRange;
    }

    public Date getDateUpperRange() {
        return dateUpperRange;
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
