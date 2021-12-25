package com.xgsama.apiuse.kafka.flink;

import com.alibaba.fastjson.JSON;
import com.github.javafaker.Faker;
import com.xgsama.apiuse.entity.Student;
import com.xgsama.apiuse.entity.StudentDetail;
import com.xgsama.apiuse.kafka.producer.KafkaProducerAnalysis;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.xgsama.apiuse.kafka.KafkaConfig.initConfig;

/**
 * Table1
 *
 * @author : xgSama
 * @date : 2021/11/4 09:46:11
 */
public class Table2 {

    private static final String joinTable1 = "zy_table_2";


    public static void main(String[] args) throws InterruptedException {
        Faker faker = new Faker(Locale.CHINA);
        int startId = 1001;
        Properties props = initConfig();
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        String[] gender = {"男", "女"};
        String[] email_suffix = "@gmail.com,@yahoo.com,@msn.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");

        while (true) {
            StudentDetail student = new StudentDetail();
            student.setId(startId++);
            student.setAddress(faker.address().fullAddress());
            student.setEmail(faker.funnyName().name().replaceAll(" ", "_") + email_suffix[faker.number().numberBetween(0, email_suffix.length - 1)]);
            student.setTel(faker.phoneNumber().cellPhone());

            producer.send(new ProducerRecord<>(joinTable1, JSON.toJSONString(student)));

//            TimeUnit.SECONDS.sleep(1);
        }
    }

    /**
     * @param china (字符串 汉字)
     * @return 汉字转拼音 其它字符不变
     */
    public static String getPinyin(String china) {
        HanyuPinyinOutputFormat formart = new HanyuPinyinOutputFormat();
        formart.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        formart.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        formart.setVCharType(HanyuPinyinVCharType.WITH_V);
        char[] arrays = china.trim().toCharArray();
        String result = "";
        try {
            for (int i = 0; i < arrays.length; i++) {
                char ti = arrays[i];
                if (Character.toString(ti).matches("[\\u4e00-\\u9fa5]")) { //匹配是否是中文
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(ti, formart);
                    result += temp[0];
                } else {
                    result += ti;
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }

        return result;
    }
}
