import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { CoursesService } from '../../services/courses.service';

// Componente inteligente organizando

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss'],
})
export class CourseFormComponent implements OnInit {
  //Formulário tipado com campos não nulos
  form = this.formBuilder.group({ name: [''], category: [''] });
  durationInSeconds = 4;
  constructor(
    //Não permite campos nulos no formulário
    private formBuilder: NonNullableFormBuilder,
    private service: CoursesService,
    private _snackBar: MatSnackBar,
    private location: Location
  ) {}

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
