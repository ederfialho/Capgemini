import { ContacorrenteService } from './contacorrente.service';
import { HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';



import { AppComponent } from './app.component';
import { PesquisarComponent } from './pesquisar/pesquisar.component';
import { ResultadoComponent } from './resultado/resultado.component';

@NgModule({
  declarations: [
    AppComponent,
    PesquisarComponent,
    ResultadoComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [ContacorrenteService],
  bootstrap: [AppComponent]
})
export class AppModule { }
