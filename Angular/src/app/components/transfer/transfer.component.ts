import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/service';
import { Router } from '@angular/router';
import { Account } from 'src/app/models/account';
import { Response } from 'src/app/models/response';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent implements OnInit {

  constructor(private service:UserService, private router: Router) { }

  ngOnInit() {
  }

  list: Account[] = []

  response: Response;

  transfer(from:number, to:number, amount:number){
    this.service.find(from).subscribe(
        response1=>{
          this.response = response1;
          if(this.response.status === 200){
            this.list.push(this.response.data);
            this.service.find(to).subscribe(
              response2=>{
                this.response = response2;
                if(this.response.status === 200){
                  this.list.push(this.response.data);
                  this.service.transfer(this.list, amount).subscribe(
                    response3=>{
                      this.response = response3;
                      if(this.response.status === 200){
                        alert(this.response.message)
                        this.router.navigate(['display'])
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
          }else{
            alert(this.response.message)
          }
        }
    )
  }
}
