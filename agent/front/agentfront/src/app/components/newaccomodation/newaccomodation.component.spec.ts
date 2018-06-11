import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewaccomodationComponent } from './newaccomodation.component';

describe('NewaccomodationComponent', () => {
  let component: NewaccomodationComponent;
  let fixture: ComponentFixture<NewaccomodationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewaccomodationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewaccomodationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
