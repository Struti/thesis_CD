import { Component } from '@angular/core';
import { TranslateServices } from '../../service/translate';

@Component({
  selector: 'header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  constructor(public translate: TranslateServices) {
  }
}
