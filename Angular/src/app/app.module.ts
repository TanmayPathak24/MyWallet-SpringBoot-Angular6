import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import{RouterModule,Routes} from '@angular/router'
import{ FormsModule} from '@angular/forms'
import {HttpClientModule} from '@angular/common/http'
import { UserService } from './services/service';
import { AddaccountComponent } from './components/addaccount/addaccount.component';
import { DisplayComponent } from './components/display/display.component';
import { WithdrawComponent } from './components/withdraw/withdraw.component';
import { DeleteComponent } from './components/delete/delete.component';
import { DepositComponent } from './components/deposit/deposit.component';
import { TransferComponent } from './components/transfer/transfer.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
//routing 



@NgModule({ 
  declarations: [
    AppComponent,AddaccountComponent, DisplayComponent, WithdrawComponent, DeleteComponent, DepositComponent, TransferComponent, WelcomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([ 
      {path:'', redirectTo: "/welcome", pathMatch: "full"},
      {path: "welcome", component: WelcomeComponent},
      {path: 'addaccount', component: AddaccountComponent},
      {path: 'display', component: DisplayComponent},
      {path: 'withdraw', component: WithdrawComponent},
      {path: 'delete', component: DeleteComponent},
      {path: 'deposit', component: DepositComponent},
      {path: 'transfer', component: TransferComponent}
    ]), 
    FormsModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
