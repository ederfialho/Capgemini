import { Component, Output } from '@angular/core';

@Component({
  selector: 'app-pesquisar',
  templateUrl: './pesquisar.component.html',
  styleUrls: ['../app.component.css']
})
export class PesquisarComponent {

 @Output() numeroContaCorrente = '';

  pesquisar() {
    console.log('Pesquisando ' + this.numeroContaCorrente);
  }

}
