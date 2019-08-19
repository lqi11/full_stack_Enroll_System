package com.mycompany.myapp.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Course.class)
public abstract class Course_ {

	public static volatile SingularAttribute<Course, String> courseName;
	public static volatile SingularAttribute<Course, Long> teacherId;
	public static volatile SingularAttribute<Course, String> courseLocation;
	public static volatile SingularAttribute<Course, Long> id;
	public static volatile SingularAttribute<Course, String> courseContent;

}

