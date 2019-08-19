import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CourseDto } from 'app/shared/model/course-dto.model';
import { SERVER_API_URL } from 'app/app.constants';
import { CourseWithTNDto } from 'app/shared/model/courseWithTN-dto.model';

@Injectable()
export class CourseService {
    private courseAddressUrl = SERVER_API_URL + '/api/course/findAllCoursesDto';
    private courseAddressWithTNUrl = SERVER_API_URL + '/api/course/findAllCoursesWithTNDto';
    private courseDeleteUrl = SERVER_API_URL + '/api/course/deleteCourse';
    private courseUpdateUrl = SERVER_API_URL + '/api/course/updateCourse';
    private courseRegisterUrl = SERVER_API_URL + '/api/course/registerCourse';
    private courseUnRegisterUrl = SERVER_API_URL + '/api/course/unRegisterCourse';
    private courseAddUrl = SERVER_API_URL + '/api/course/addCourse';
    private findTeacherIdUrl = SERVER_API_URL + '/api/user/findId';

    constructor(private http: HttpClient) {}

    getCourseInfo(): Observable<CourseDto[]> {
        return this.http.get<CourseDto[]>(`${this.courseAddressUrl}`);
    }

    getEnrolledCourseInfoWithTN(): Observable<CourseWithTNDto[]> {
        return this.http.get<CourseWithTNDto[]>(`${this.courseAddressWithTNUrl}`);
    }

    delete(courseName: String): Observable<Response> {
        debugger;
        return this.http.delete<Response>(`${this.courseDeleteUrl}/${courseName}`);
    }

    update(course: CourseDto): Observable<Response> {
        return this.http.put<Response>(this.courseUpdateUrl, course);
    }
    registerCourse(courseName: String): Observable<Response> {
        // debugger;
        const res = this.http.post<Response>(`${this.courseRegisterUrl}/${courseName}`, courseName);
        console.log(res);
        return res;
    }
    unRegisterCourse(courseName: String): Observable<Response> {
        return this.http.delete<Response>(`${this.courseUnRegisterUrl}/${courseName}`);
    }

    findTeacherId(): Observable<String> {
        return this.http.get<String>(`${this.findTeacherIdUrl}`);
    }
    findCourseById(id: String): Observable<CourseWithTNDto[]> {
        return this.http.get<CourseWithTNDto[]>(`${this.courseAddressWithTNUrl}/${id}`);
    }
    getCourseInfoWithTN(id: String): Observable<CourseWithTNDto[]> {
        return this.http.get<CourseWithTNDto[]>(`${this.courseAddressWithTNUrl}/${id}`);
    }
    addCourse(course: CourseDto): Observable<Response> {
        return this.http.post<Response>(`${this.courseAddUrl}`, course);
    }
}
