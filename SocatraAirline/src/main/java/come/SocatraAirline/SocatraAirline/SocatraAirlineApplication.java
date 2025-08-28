package come.SocatraAirline.SocatraAirline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SocatraAirlineApplication {
    @Autowired
    private JavaMailSender javaMailSender ;

    public static void main(String[] args) {
        SpringApplication.run(SocatraAirlineApplication.class, args);
    }}
   /* @Bean
    CommandLineRunner runner(){
        return  args -> {
            try {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(
                        mimeMessage,
                        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                        StandardCharsets.UTF_8.name()
                );
                helper.setTo("Abdullahghallab20@gmail.com");
                helper.setSubject("Hello Testing");
                helper.setText("Testing email 123456");
                System.out.println("Email Sent");
                javaMailSender.send(mimeMessage);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());

            }
            ;

        };
    }

}*/
