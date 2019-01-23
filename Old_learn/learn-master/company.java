
public class Company{

    private String company_name;
    private String city;
    private List<Employee> employee;


    @Override
    public String toString() {
        return company_name + " | " + city+ " \n " + employee + "\n";
    }


}
