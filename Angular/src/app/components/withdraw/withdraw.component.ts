import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/service';
import {Account} from '../../models/account'
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';
import { Response } from 'src/app/models/response';

@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.css']
})
export class WithdrawComponent implements OnInit {

  constructor(private service: UserService, private route: Router) { }

  ngOnInit() {
  }


  amount: number;
  mobile: number;

  
  response: Response;

  withdraw():void{
    this.service.find(this.mobile).subscribe(
      res1=>{
        this.response = res1
        if(this.response.status === 200){
          this.service.withdraw(this.response.data, this.amount).subscribe(
            res2=>{
              this.response = res2
              if(this.response.status === 200){
                alert(this.response.message)
                this.route.navigate(['display'])
              }else{
                alert(this.response.message)
              }
            }
          )
        }else{
          alert(this.response.message)
        }
      }
    )
  }
  
}
