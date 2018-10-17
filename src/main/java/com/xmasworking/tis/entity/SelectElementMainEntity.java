package com.xmasworking.tis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/12 - 下午4:48
 * Created by IntelliJ IDEA.
 */
@Entity
@Data
@Table(name = "selectelementmain")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class SelectElementMainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String selectname;
    String selectdescribe;

//    //cascade:表的级联操作
//    //JPA注释： 一对一 关系
//    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.REMOVE,targetEntity = SelectElementDetailEntity.class)
//    /**
//     * referencedColumnName：参考列名,默认的情况下是列表的主键
//     * nullable=是否可以为空，
//     * insertable：是否可以插入，
//     * updatable：是否可以更新
//     * columnDefinition=列定义，
//     * foreignKey=外键
//     */
//    @JoinColumn(name="mainid")
//    List<SelectElementDetailEntity> selectElementDetailEntityList;
}
