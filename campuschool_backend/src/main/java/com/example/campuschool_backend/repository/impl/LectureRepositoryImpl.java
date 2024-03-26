package com.example.campuschool_backend.repository.impl;

import com.example.campuschool_backend.domain.lecture.*;
import com.example.campuschool_backend.domain.lecture.enums.RegisterStatus;
import com.example.campuschool_backend.dto.lecture.LectureSearchParam;
import com.example.campuschool_backend.repository.CustomLectureRepository;
import com.example.campuschool_backend.repository.LectureRepository;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class LectureRepositoryImpl implements CustomLectureRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Lecture> findPopularLectures() {
        QLecture qLecture = new QLecture("l");
        return queryFactory.selectFrom(qLecture)
                .orderBy(qLecture.views.desc())
                .limit(5)
                .fetch();
    }

    @Override
    public List<Lecture> findRecentLectures() {
        QLecture qLecture = new QLecture("l");
        return queryFactory.selectFrom(qLecture)
                .orderBy(qLecture.createdAt.desc())
                .limit(5)
                .fetch();
    }

    @Override
    public Page<Lecture> findLectures(LectureSearchParam lectureSearchParam, Pageable pageable) {
        QLecture qLecture = QLecture.lecture;
        List<Lecture> lectureList = queryFactory.selectFrom(qLecture)
                .where(searchProducts(qLecture,lectureSearchParam))
                .orderBy(lectureSort(qLecture,pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = queryFactory.select(qLecture.count())
                .from(qLecture)
                .where(searchProducts(qLecture,lectureSearchParam))
                .fetchOne();
        return new PageImpl<>(lectureList,pageable,count);
    }

    @Override
    public List<Lecture> findMyOpenLectures(Long id) {
        QLecture qlecture = QLecture.lecture;
        List<Lecture> lectureList = queryFactory.selectFrom(qlecture)
                .where(myLecture(qlecture,id))
                .fetch();
        return lectureList;
    }
    @Override
    public List<Lecture> findMyRegisterLectures(Long id) {
        QLecture lecture = QLecture.lecture;
        QRegister register = QRegister.register;
        return queryFactory.selectFrom(lecture)
                .innerJoin(lecture.registerList, register)
                .where(register.user.id.eq(id)
                        .and(register.status.eq(RegisterStatus.COMPLETE)))
                .fetch();
    }
    @Override
    public List<Lecture> findMyWaitLectures(Long id) {
        QLecture lecture = QLecture.lecture;
        QRegister register = QRegister.register;
        return queryFactory.selectFrom(lecture)
                .innerJoin(lecture.registerList, register)
                .where(register.user.id.eq(id)
                        .and(register.status.eq(RegisterStatus.WAIT)))
                .fetch();
    }

    @Override
    public Page<Review> findReview(Long id, Pageable pageable) {
        QLecture qLecture = QLecture.lecture;
        QReview review = QReview.review;
        BooleanExpression condition = review.lecture.id.eq(id);

        List<Review> reviewList = queryFactory.selectFrom(review)
                .where(condition)
                .orderBy(new OrderSpecifier<>(Order.DESC, review.createdAt))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory.select(review.count())
                .from(review)
                .where(condition)
                .fetchOne();
        return new PageImpl<>(reviewList,pageable, count);
    }

    @Override
    public List<Review> findRecentReviews() {
        QReview review = QReview.review;
        return queryFactory.selectFrom(review)
                .orderBy(new OrderSpecifier<>(Order.DESC, review.createdAt))
                .limit(5)
                .fetch();
    }

    @Override
    public Page<Notification> findNotification(Long id, Pageable pageable) {
        QLecture qLecture = QLecture.lecture;
        BooleanExpression condition = qLecture.id.eq(id);
        Lecture lecture = queryFactory
                .selectFrom(qLecture)
                .where(condition)
                .fetchOne();
        return new PageImpl<>(lecture.getNotificationList(),pageable,lecture.getNotificationList().size());
    }

    private Predicate searchProducts(QLecture lecture, LectureSearchParam lectureSearchParam) {
        BooleanExpression predicate = Expressions.TRUE;
        if(lectureSearchParam.getCategoryType() != null) {
            predicate = predicate.and(lecture.categoryType.eq(lectureSearchParam.getCategoryType()));
        }
        if(lectureSearchParam.getDifficulty() != null) {
            predicate = predicate.and(lecture.difficulty.eq(lectureSearchParam.getDifficulty()));
        }
        if(lectureSearchParam.getKeyword() != null) {
            predicate = predicate.and(lecture.title.contains(lectureSearchParam.getKeyword()));
        }

        return predicate;
    }
    private Predicate myLecture(QLecture lecture, Long id) {
        BooleanExpression predicate = Expressions.TRUE;
        predicate = predicate.and(lecture.teacher.id.eq(id));
        return predicate;
    }
    private Predicate thisLecture(QLecture lecture, Long id) {
        BooleanExpression predicate = Expressions.TRUE;
        predicate = predicate.and(lecture.id.eq(id));
        return predicate;
    }
    private OrderSpecifier<?> lectureSort(QLecture lecture, Pageable page) {
        //서비스에서 보내준 Pageable 객체에 정렬조건 null 값 체크
        if (!page.getSort().isEmpty()) {
            //정렬값이 들어 있으면 for 사용하여 값을 가져온다
            for (Sort.Order order : page.getSort()) {
                // 서비스에서 넣어준 DESC or ASC 를 가져온다.
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                // 서비스에서 넣어준 정렬 조건을 스위치 케이스 문을 활용하여 셋팅하여 준다.
                switch (order.getProperty()){
                    case "views":
                        return new OrderSpecifier(direction, lecture.views);
                    case "createdAt":
                        return new OrderSpecifier(direction, lecture.createdAt);
                }
            }
        }
        return null;
    }

}
