package lombok;

import java.util.Date;

/**
 * @Author: qixiujuan
 * @Describe Student
 * @Date: 2019/1/10
 * @Modified By：
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true,of = "id")
public class Student extends Parent{

    private String code;
    private String name;
    private Integer grande;
    private String schoolCode;
    private Integer status;
    private Date beginTime;

}
