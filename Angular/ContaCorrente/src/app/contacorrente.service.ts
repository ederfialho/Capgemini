import { Http } from '@angular/http';
import { Injectable } from '@angular/core';

import 'rxjs';

@Injectable()
export class ContacorrenteService {

  constructor(private http: Http) { }

  consultar(): Promise<any> {
    return this.http.get('http://localhost:8080/contacorrente')
      .toPromise()
      .then(response => response.json());
  }

  consultarContaCorrente(id: number): Promise<any> {
    if (id.toString() === '') {
      this.consultar();
    }
    return this.http.get(`http://localhost:8080/contacorrente/${id}`)
      .toPromise()
      .then(response => response.json());
  }

  saque(id: number, valor: number, contacorrente: any): Promise<any> {
    return this.http.post(`http://localhost:8080/contacorrente/sacar/${id}/${valor}`, contacorrente)
      .toPromise()
      .then(response => response.json())
      .catch(erro => {
        return Promise.reject(`Erro ao sacar na conta ${contacorrente.idConta}.`);
      });
  }

  deposito(id: number, valor: number, contacorrente: any): Promise<any> {
    return this.http.post(`http://localhost:8080/contacorrente/depositar/${id}/${valor}`, contacorrente)
      .toPromise()
      .then(response => response.json())
      .catch(erro => {
        return Promise.reject(`Erro ao depositar na conta ${contacorrente.idConta}.`);
      });
  }
}
