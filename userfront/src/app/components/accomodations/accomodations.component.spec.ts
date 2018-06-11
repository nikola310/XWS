import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccomodationsComponent } from './accomodations.component';

describe('AccomodationsComponent', () => {
  let component: AccomodationsComponent;
  let fixture: ComponentFixture<AccomodationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccomodationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccomodationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
