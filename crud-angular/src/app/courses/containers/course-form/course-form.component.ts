import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { Course } from '../../model/course';
import { CoursesService } from '../../services/courses.service';

// Componente inteligente organizando

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss'],
})
export class CourseFormComponent implements OnInit {
  //Formulário tipado com campos não nulos
  form = this.formBuilder.group({
    _id: [''],
    name: [
      '',
      [Validators.required, Validators.minLength(4), Validators.maxLength(100)],
    ],
    category: ['', [Validators.required]],
  });
  durationInSeconds = 4;
  constructor(
    //Não permite campos nulos no formulário
    private formBuilder: NonNullableFormBuilder,
    private service: CoursesService,
    private _snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    //recebendo copia da rota
    const course: Course = this.route.snapshot.data['course'];
    //inicializando campos com valores
    this.form.setValue({
      _id: course._id,
      name: course.name,
      category: course.category,
    });
  }

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
    this._snackBar.open('Curso salvo com sucesso! 😄', '', {
      duration: this.durationInSeconds * 1000,
    });
    this.form.reset();
    this.onCancel();
  }

  getErrorMessage(fieldName: string) {
    const field = this.form.get(fieldName);

    if (field?.hasError('required')) {
      return 'Campo obrigatório';
    }

    if (field?.hasError('minlength')) {
      const requiredLength: number = field.errors
        ? field.errors['minlength']['requiredLength']
        : 4;

      return `Tamanho mínimo precisa ser de ${requiredLength} caracteres`;
    }

    if (field?.hasError('maxlength')) {
      const requiredLength: number = field.errors
        ? field.errors['maxlength']['requiredLength']
        : 100;

      return `Tamanho máximo excedido de ${requiredLength} caracteres`;
    }

    return 'Campo invalido';
  }
}
