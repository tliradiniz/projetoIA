import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const EMAIL_URL = 'http://localhost:8080/api/email/';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  constructor(private http: HttpClient) { }

  sendMessage(id, data) {
    return this.http.post(`${EMAIL_URL}${id}`, data);
  }
}
