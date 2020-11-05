package c2.elastic.bucket.EvidenceService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        Employee emp1 = Employee.builder().name("Abc").age(24).org("feg").income(345.60).build();
        Employee emp2 = Employee.builder().name("poi").age(29).org("feg").income(345.60).build();
        List<Employee> empList = Arrays.asList(emp1, emp2);
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("[");
//        List<String> empListJson = empList.stream().map(emp-> {
//            try {
//                return objectMapper.writeValueAsString(emp);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }).collect(Collectors.toList());
//        stringBuilder.append(String.join(",", empListJson));
//        stringBuilder.append("]");
//        stringBuilder.append("[");xx
//        String json = objectMapper.writeValueAsString(emp);
//        System.out.println(json);
//        stringBuilder.append(json);
//        stringBuilder.append("]");
//        String filename = "/Users/shradhashankar/Desktop/ElasticBucket/logs/EvidenceServiceLogs/test.json";
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));
////        bufferedWriter.write(stringBuilder.toString());
//        bufferedWriter.write(objectMapper.writeValueAsString(empList));
//        bufferedWriter.close();
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd-hh-mm-ss");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//        System.out.println(simpleDateFormat.format(date));
//        simpleDateFormat = new SimpleDateFormat("MMMM");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//        System.out.println(simpleDateFormat.format(date));
//        simpleDateFormat = new SimpleDateFormat("YYYY");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//        System.out.println(simpleDateFormat.format(date));
//        String dirpath = "/Users/shradhashankar/Desktop/ElasticBucket/logs/EvidenceServiceLogs/Event3/newE/";
//        File directory = new File(dirpath);
//        if(!directory.exists()){
//            directory.mkdirs();
//        }
        String path = "/Users/shradhashankar/Desktop/ElasticBucket/logs/EvidenceServiceLogs/dir2";
        File dir = new File(path);
        System.out.println(dir.mkdirs());
        //Files.createDirectories(Paths.get(path));
    }

    void createFolder(File file) {
        if (file.exists()) {
            synchronized (this){
                if(file.exists()){
                    file.mkdirs();
                }
            }
        }
    }
}

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
abstract class User {
    private String name;
    private int age;
}

@SuperBuilder(toBuilder = true)
@Data
class Employee extends User {
    private String org;
    private double income;

    public Employee(String name, int age) {
        super(name, age);
    }
}
