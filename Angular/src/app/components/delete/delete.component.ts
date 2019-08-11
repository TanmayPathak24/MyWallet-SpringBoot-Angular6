import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/service';
import{Account} from "..//../models/account"
import { Router } from '@angular/router';
import {Response } from 'src/app/models/response';
@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteComponent implements OnInit {

  constructor(private service: UserService, private route:Router) { }

  ngOnInit() {
  }

  account: Account = null;
  response: Response;


  fetchDetails(mobile: number){
    if(mobile+"" === "") alert("Mobile number can not be empty");
    else
    this.service.find(mobile).subscribe(
      data=>{
        this.response = data;
        if(this.response.status === 200){
          this.account = this.response.data;
        }else{
          alert(this.response.message);
        }
      }
    )
  }

  delete():any{
    this.service.delete(this.account).subscribe(
      res=>{
        this.response = res;
        if(this.response.status === 200){
          alert(this.response.message)
          this.route.navigate(['display']);
        }else{
          alert(this.response.message)
        }
      }
    )
  }

}
