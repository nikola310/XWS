import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BonusTypesComponent } from './bonus-types.component';

describe('BonusTypesComponent', () => {
  let component: BonusTypesComponent;
  let fixture: ComponentFixture<BonusTypesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BonusTypesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BonusTypesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
