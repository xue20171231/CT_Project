import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 生产日志
 */
public class ProductLog {

    //存放生产的电话号码
    private List<String> phoneList = new ArrayList<>();

    //存放生产的电话号码和姓名
    private Map<String,String> phoneNameMap = new HashMap<>();

    //随机时间的启示时间
    String startTime = "2018-01-01";
    //随机时间的结束时间
    String endTime = "2018-12-31";

    /**
     * 初始化电话号码
     */
    public void initPhone(){
        phoneList.add("17078388295");
        phoneList.add("13980337439");
        phoneList.add("14575535933");
        phoneList.add("19902496992");
        phoneList.add("18549641558");
        phoneList.add("17005930322");
        phoneList.add("18468618874");
        phoneList.add("18576581848");
        phoneList.add("15978226424");
        phoneList.add("15542823911");
        phoneList.add("17526304161");
        phoneList.add("15422018558");
        phoneList.add("17269452013");
        phoneList.add("17764278604");
        phoneList.add("15711910344");
        phoneList.add("15714728273");
        phoneList.add("16061028454");
        phoneList.add("16264433631");
        phoneList.add("17601615878");
        phoneList.add("15897468949");

        phoneNameMap.put("17078388295", "李雁");
        phoneNameMap.put("13980337439", "卫艺");
        phoneNameMap.put("14575535933", "仰莉");
        phoneNameMap.put("19902496992", "陶欣悦");
        phoneNameMap.put("18549641558", "施梅梅");
        phoneNameMap.put("17005930322", "金虹霖");
        phoneNameMap.put("18468618874", "魏明艳");
        phoneNameMap.put("18576581848", "华贞");
        phoneNameMap.put("15978226424", "华啟倩");
        phoneNameMap.put("15542823911", "仲采绿");
        phoneNameMap.put("17526304161", "卫丹");
        phoneNameMap.put("15422018558", "戚丽红");
        phoneNameMap.put("17269452013", "何翠柔");
        phoneNameMap.put("17764278604", "钱溶艳");
        phoneNameMap.put("15711910344", "钱琳");
        phoneNameMap.put("15714728273", "缪静欣");
        phoneNameMap.put("16061028454", "焦秋菊");
        phoneNameMap.put("16264433631", "吕访琴");
        phoneNameMap.put("17601615878", "沈丹");
        phoneNameMap.put("15897468949", "褚美丽");
    }

    /**
     * 生产数据 类型：16264433631,15714728273,2018-12-21 15:55:45,1595
     *   对应名称：caller(主叫),callee(被叫),buildTime(通话建立时间) ,Duration(通话时长)
     */
    public String product(){
        //生成主叫的随机索引
        int callerIndex = (int)(Math.random() * phoneList.size());
        //通过随机索引获取主叫的电话号码
        String caller = phoneList.get(callerIndex);
        //通过主叫的电话号码，获取主叫的姓名
        String callerName = phoneNameMap.get(caller);
        String callee = "";
        String calleeName = "";
        while(true){
            //被叫的生成策略和主叫的一样
            int calleeIndex = (int)(Math.random() * phoneList.size());
            callee = phoneList.get(calleeIndex);
            calleeName = phoneNameMap.get(callee);
            if(!caller.equals(callee)){
                break;
            }
        }
        //产生随机建立时间
        String buildTime = randomBuildTime(startTime, endTime);
        //随机产生通话持续时间
        DecimalFormat df = new DecimalFormat("0000");
        String duration = df.format((int) 30 * 60 * Math.random());
        StringBuilder sb = new StringBuilder();
        sb.append(caller+",").append(callee+",").append(buildTime+",").append(duration);
        return sb.toString();
    }

    /**
     * 随机产生时间
     * @param startTime 起始时间
     * @param endTime 结束时间
     * @return 时间字符串
     */
    private String randomBuildTime(String startTime,String endTime) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = sdf1.parse(startTime);
            Date endDate = sdf1.parse(endTime);
            if (endDate.getTime() <= startDate.getTime()) {
                return null;
            }
            long randomTS = startDate.getTime() + (long) ((endDate.getTime() - startDate.getTime()) * Math.random());
            Date resultDate = new Date(randomTS);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String resultTime = sdf2.format(resultDate);
            return resultTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeLog(String filePath){
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath,true), "UTF-8");
            while(true){
                Thread.sleep(500);
                String log = product();
                System.out.println(log);
                osw.write(log+"\n");
                osw.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //args =new String[]{"E:\\calllog.csv"};
        ProductLog productLog = new ProductLog();
        productLog.initPhone();
        productLog.writeLog(args[0]);
    }
}
