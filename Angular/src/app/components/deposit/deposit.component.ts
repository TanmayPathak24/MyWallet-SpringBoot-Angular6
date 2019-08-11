import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/service';
import { Router } from '@angular/router';
import {Response} from "../../models/response"
import { Account } from 'src/app/models/account';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent implements OnInit {

  constructor(private service:UserService, private router:Router) { }

  ngOnInit() {
  }
  
  response: Response;

  deposit(mobile:number, amount:number):any{
    if(mobile+"" === "") alert("Mobile number Can not be empty")
    else if(amount+"" === "") alert("Amount can not be empty")
    else{
      this.service.find(mobile).subscribe(
        res=>{
          this.response = res;
          if(this.response.status === 200){
            this.service.deposit(this.response.data, amount).subscribe(
              response2=>{
                this.response = response2;
                if(this.response.status === 200){
                  alert(this.response.message)
                  this.router.navigate(['display'])
                }else{
                  alert(this.response.message)
                }
              }
            )     
          }else{
            alert(this.response.message);
          }
        }
      )
    }
  }
  
}
