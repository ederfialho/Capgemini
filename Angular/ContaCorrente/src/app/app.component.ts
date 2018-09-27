import { Component, OnInit } from '@angular/core';

import { ContacorrenteService } from './contacorrente.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  implements OnInit {

  contacorrente = [];
  listaCasting = [];
  valorOperacao = 0;
  nome = '';

  constructor(private ccs: ContacorrenteService) {}

  ngOnInit() {  }

  consultar() {
    this.ccs.consultar()
      .then(dados => {
        console.log(dados);
        this.contacorrente = dados;
      });
  }
  consultarContaCorrente(idConta: number) {
    this.ccs.consultarContaCorrente(idConta)
      .then(dados => {
        if ((Array.isArray(dados)) === true) {
          this.contacorrente = dados;
        } else {
          this.listaCasting = [];
          this.listaCasting.push(dados);
          this.contacorrente = this.listaCasting;
        }
      });
  }

  saque(id: number, valor: number, contacorrente: any) {
    this.ccs.saque(id, valor, contacorrente)
      .then((dados) => {
        alert('Saque realizado com sucesso!');
        if ((Array.isArray(dados)) === true) {
          this.contacorrente = dados;
        } else {
          this.listaCasting = [];
          this.listaCasting.push(dados);
          this.contacorrente = this.listaCasting;
        }
      })
      .catch(erro => {
        alert(erro);
      });
  }

  deposito(id: number, valor: number, contacorrente: any) {
    this.ccs.deposito(id, valor, contacorrente)
      .then((dados) => {
        alert('Deposito realizado com sucesso!');
        if ((Array.isArray(dados)) === true) {
          this.contacorrente = dados;
        } else {
          this.listaCasting = [];
          this.listaCasting.push(dados);
          this.contacorrente = this.listaCasting;
        }
      })
      .catch(erro => {
        alert(erro);
      });
  }
}
