import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Resolve,
  RouterStateSnapshot,
} from '@angular/router';
import { Observable, of } from 'rxjs';

import { Course } from '../model/course';
import { CoursesService } from '../services/courses.service';

@Injectable({
  providedIn: 'root',
})
export class CourseResolver implements Resolve<Course> {
  constructor(private service: CoursesService) {}
  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Course> {
    if (route.params && route.params['id']) {
      return this.service.getCourseById(route.params['id']);
    }
    //Retorna um observable de course vazio
    return of({ _id: '', name: '', category: '' });
  }
}
