import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import {Http} from "@angular/http";
import { Observable } from "rxjs";
import {Account} from "../models/account"
import {Response} from "../models/response"


@Injectable({
    providedIn: 'root'
})

export class UserService{

    url = "http://localhost:5000/"

    constructor(private http:HttpClient){}
    //constructor(private http:Http){}
    
    myresponse: any;


    addAccount(account: Account): Observable<Response>{ // checked
        let options = {
            "headers":
            new HttpHeaders({"Content-Type":"application/json"})
        }

        return this.http.post<Response>(this.url+"addaccount", account, options)
    }

    withdraw(account: Account, amount: number):Observable<Response>{  // checked
        let options = {
            "headers":
            new HttpHeaders({"Content-Type":"application/json"})
        }
        return this.http.post<Response>(this.url+"withdraw?amount="+amount, account, options);
    }

    deposit(account: Account, amount: number):Observable<Response>{
        let options = {
            "headers":
            new HttpHeaders({"Content-Type":"application/json"})
        } 
        return this.http.post<Response>(this.url+"deposit?amount="+amount, account, options);
    }

    transfer(list: Account[], amount: number):Observable<Response>{
        let options = {
            "headers":
            new HttpHeaders({"Content-Type":"application/json"})
        }
        return this.http.post<Response>(this.url+"transfer?amount="+amount, list, options);
    }

    delete(account: Account):Observable<Response>{ // checked
        let options = {
            "headers":
            new HttpHeaders({"Content-Type":"application/json"})
        }
        return this.http.post<Response>(this.url+"delete", account, options);
    }

    find(mobileno: number):Observable<Response>{ // checked
        let options = {
            "headers":
            new HttpHeaders({"Content-Type":"application/json"})
        }
        return this.http.post<Response>(this.url+"find?mobileNo="+mobileno, options);
    }

    getall(): Observable<any>{ // checked
        let options = {
            "headers":
            new HttpHeaders({"Content-Type":"application/json"})
        }
        return this.http.get<any>(this.url+"displayAll", options);
    }
}