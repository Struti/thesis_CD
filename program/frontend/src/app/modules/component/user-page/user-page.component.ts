import { Component, OnInit } from '@angular/core';
import { MockTableService } from '../../service/mock-table.service';
import { Table } from '../../service/table';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.scss']
})
export class UserPageComponent implements OnInit {

  public mockTablesDatas: Table[] = [];

  constructor(
    private mockTableService: MockTableService,
  ) { }

  ngOnInit(): void {

    this.mockTableService.mockData.forEach(data => {
      this.mockTablesDatas.push({
        id: data.id,
        first_name: data.first_name,
        last_name: data.last_name,
        email: data.email
      });
    });
  }
}
