import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccCategoryComponent } from './acc-category.component';

describe('AccCategoryComponent', () => {
  let component: AccCategoryComponent;
  let fixture: ComponentFixture<AccCategoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccCategoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
