import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/service';
import {Account} from '../../models/account'
import { Router } from '@angular/router';
import { Response } from "../../models/response"
@Component({
  selector: 'app-addaccount',
  templateUrl: './addaccount.component.html',
  styleUrls: ['./addaccount.component.css']
})
export class AddaccountComponent implements OnInit {

  constructor(private service: UserService, private route: Router) { }

  ngOnInit() {
  }

  id: number;
  holdername: string;
  mobileno: number;
  balance: number;

  myaccount: Account = new Account();
  response: Response;


  addAccount(){
    var acc: Account = new Account();
    acc.id = this.id;
    acc.balance = this.balance; 
    acc.holdername = this.holdername;
    acc.mobileno = this.mobileno;
    this.service.addAccount(acc).subscribe(
      res=>{
        this.response = res;
        if(this.response.status === 200){
          alert(this.response.message)
          this.route.navigate(['display'])
        }else{
          alert(this.response.message)
        }
      }
    );
  }
}
