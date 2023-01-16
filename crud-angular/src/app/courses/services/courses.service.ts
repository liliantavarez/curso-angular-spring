import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Course } from '../model/course';
import { first, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CoursesService {
  [x: string]: any;
  private readonly API = 'api/courses';

  constructor(private httpClient: HttpClient) {}

  //Partial: aceita objeto com pelo menos um atributo da interface (parcialmente)
  postCourse(course: Partial<Course>) {
    return this.httpClient.post<Course>(this.API, course).pipe(first());
  }

  getCourses() {
    return this.httpClient.get<Course[]>(this.API).pipe(first());
  }

  getCourseById() {
    return this.httpClient.get<Course>(this.API).pipe(first());
  }

  deleteCouseById(_id: String) {
    return this.httpClient.delete<Course>(this.API).pipe(first());
  }
}
