import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { CoursesService } from '../services/courses.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss'],
})
export class CourseFormComponent implements OnInit {
  form: FormGroup;
  durationInSeconds = 4;
  constructor(
    private formBuilder: FormBuilder,
    private service: CoursesService,
    private _snackBar: MatSnackBar,
    private location: Location
  ) {
    this.form = this.formBuilder.group({ name: [null], category: [null] });
  }

  ngOnInit(): void {}

  onSubmit() {
    this.service.postCourse(this.form.value).subscribe(
      (result) => this.onSucess(),
      (error) => this.onErro()
    );
  }

  onCancel() {
    this.location.back();
  }

  private onErro() {
    this._snackBar.open('Erro ao salvar curso', '', {
      duration: this.durationInSeconds * 1000,
    });
  }
  private onSucess() {
    this._snackBar.open('Curso salvo!', '', {
      duration: this.durationInSeconds * 1000,
    });
    this.form.reset();
    this.onCancel();
  }
}
