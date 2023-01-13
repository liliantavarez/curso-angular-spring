import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Course } from '../model/course';
import { first, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CoursesService {
  private readonly API = 'api/courses';

  constructor(private httpClient: HttpClient) {}

  getCourses() {
    return this.httpClient.get<Course[]>(this.API).pipe(first());
  }

  postCourse(course: Course) {
   return this.httpClient.post<Course>(this.API, course).pipe(first());
  }
}
