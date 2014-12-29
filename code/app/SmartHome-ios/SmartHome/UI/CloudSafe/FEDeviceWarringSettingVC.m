//
//  FEDeviceWarringSettingVC.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEDeviceWarringSettingVC.h"
#import "FEDeviceInfoView.h"
#import "FESensor.h"
#import "FEWebServiceManager.h"
#import "FEMarkSetRequest.h"
#import "FESensorSetRequest.h"
#import "FESensorSetResponse.h"
#import "FEBaseResponse.h"
#import "FEUserMark.h"
#import "AppDelegate.h"
#import "CDUser.h"
#import "FECoreDataHandler.h"
#import "FEPopPickerView.h"

@interface FEDeviceWarringSettingVC ()<FEPopPickerViewDataSource,FEPopPickerViewDelegate>

@property (nonatomic, strong) FESensor *sensor;
@property (nonatomic, strong) NSArray *markList;
@property (nonatomic, strong) UIScrollView *scrollView;
@property (nonatomic, strong) FEUserMark *seletedMark;
@property (nonatomic, strong) UITextField *descTextField;


@end

@implementation FEDeviceWarringSettingVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

-(id)initWithSensor:(FESensor *)sensor markList:(NSArray *)marks{
    self = [super init];
    if (self) {
        _sensor = sensor;
        _markList = marks;
        self.title = sensor.sensorTypeName;
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initUI];
}

-(void)initUI{
    
    UIScrollView *scrollview = [[UIScrollView alloc] initWithFrame:self.view.bounds];
    scrollview.userInteractionEnabled = YES;
    [self.view addSubview:scrollview];
    self.scrollView = scrollview;
    
    FEDeviceInfoView *dview = [[FEDeviceInfoView alloc] initWithFrame:CGRectMake(0, 0, self.view.bounds.size.width, 140)];
    dview.deviceNumber.text = [NSString stringWithFormat:@"%@",self.sensor.id];
    dview.controlPoint.text = [NSString stringWithFormat:@"%@",self.sensor.concentratorID];
    dview.deviceType.text = self.sensor.sensorTypeName;
    [scrollview addSubview:dview];
    
    UIView *contentview = [[UIView alloc] initWithFrame:CGRectMake(0, dview.bounds.size.height, self.view.bounds.size.width, 260)];
    contentview.backgroundColor = [UIColor whiteColor];
    [scrollview addSubview:contentview];
    
    
    CGFloat x = 10; //label 起始X
    CGFloat y = 20; //label 起始Y
    CGFloat xspace = 10; //space of item x
    CGFloat yspace = 20; //space of item y
    CGFloat lwidth = 60; //label width
    CGFloat twidth = self.view.bounds.size.width - (x + lwidth + xspace + 30); //textFeild width
    CGFloat height = 30;  //item (label or textFeild height)
    
    
    FELabel *dlabel = [[FELabel alloc] initWithFrame:CGRectMake(x, y, lwidth, height)];
    dlabel.text = FEString(@"SENSOR_DESCRIPETION");
    [contentview addSubview:dlabel];
    
    FETextField *dtextFeild = [[FETextField alloc] initWithFrame:CGRectMake(x + lwidth + xspace, y, twidth, height)];
    dtextFeild.text = self.sensor.descriptions;
    self.descTextField = dtextFeild;
    [contentview addSubview:dtextFeild];
    
    FELabel *elabel = [[FELabel alloc] initWithFrame:CGRectMake(x, y + height + yspace, lwidth, height)];
    elabel.text = FEString(@"SENSOR_EARLY_WARRING");
    [contentview addSubview:elabel];
    
    FETextField *etextFeild = [[FETextField alloc] initWithFrame:CGRectMake(x + lwidth + xspace, y + height + yspace, twidth, height)];
    etextFeild.text = [NSString stringWithFormat:@"%@",self.sensor.errorValue];
    [contentview addSubview:etextFeild];
    
    FELabel *vlabel = [[FELabel alloc] initWithFrame:CGRectMake(x, y + 2 * (height + yspace), lwidth, height)];
    vlabel.text = FEString(@"SENSOR_FIRE_VALUE");
    [contentview addSubview:vlabel];
    
    FETextField *vtextFeild = [[FETextField alloc] initWithFrame:CGRectMake(x + lwidth + xspace, y + 2 * (height + yspace), twidth, height)];
    vtextFeild.text = [NSString stringWithFormat:@"%@",self.sensor.errorValue];
    [contentview addSubview:vtextFeild];
    
    FELabel *llabel = [[FELabel alloc] initWithFrame:CGRectMake(x, y + 3 * (height + yspace), lwidth, height)];
    llabel.text = FEString(@"SENSOR_LABEL");
    [contentview addSubview:llabel];
    
    FEPopPickerView *popview = [[FEPopPickerView alloc] initWithFrame:CGRectMake(x + lwidth + xspace, y + 3 * (height + yspace), twidth - 60, height)];
    popview.dataSource = self;
    popview.delegate = self;
    NSInteger index = -1;
    for (FEUserMark *mark in self.markList) {
        if ([mark.mark isEqualToString:self.sensor.mark]) {
            index = [self.markList indexOfObject:mark];
            self.seletedMark = mark;
            break;
        }
    }
    [popview setSelected:index];
    [contentview addSubview:popview];
    
//    FETextField *ltextFeild = [[FETextField alloc] initWithFrame:CGRectMake(x + lwidth + xspace, y + 3 * (height + yspace), twidth - 60, height)];
//    ltextFeild.text = self.sensor.mark;
//    [contentview addSubview:ltextFeild];
    
    FEButton *add = [FEButton buttonWithType:UIButtonTypeCustom];
    add.frame = CGRectMake(x + lwidth + xspace + twidth - 50, y + 3 * (height + yspace), 50, height);
//    [add addTarget:self action:@selector(addUserMark:) forControlEvents:UIControlEventTouchUpInside];
    [add setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [add setBackgroundImage:[UIImage imageFromColor:FEGrayButtonColor] forState:UIControlStateNormal];
    [add setTitle:FEString(@"SENSOR_ADD") forState:UIControlStateNormal];
    [contentview addSubview:add];
    
    FELabel *clabel = [[FELabel alloc] initWithFrame:CGRectMake(x, y + 4 * (height + yspace), lwidth, height)];
    clabel.text = FEString(@"SENSOR_CONTROL");
    [contentview addSubview:clabel];
    
    FETextField *ctextFeild = [[FETextField alloc] initWithFrame:CGRectMake(x + lwidth + xspace, y + 4 * (height + yspace), twidth, height)];
    [contentview addSubview:ctextFeild];
    
    FEButton *configbutton = [FEButton buttonWithType:UIButtonTypeCustom];
    configbutton.autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleBottomMargin;
    configbutton.frame = CGRectMake(20, contentview.frame.origin.y + contentview.bounds.size.height + 20, self.view.bounds.size.width - 20 * 2, 40);
    [configbutton setTitle:FEString(@"SENSOR_CONFIG") forState:UIControlStateNormal];
    [configbutton addTarget:self action:@selector(config:) forControlEvents:UIControlEventTouchUpInside];
    [scrollview addSubview:configbutton];
    
//    FEButton *monitor = [FEButton buttonWithType:UIButtonTypeCustom];
//    monitor.autoresizingMask = UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleBottomMargin;
//    monitor.frame = CGRectMake(self.view.bounds.size.width - 20 - 100, contentview.frame.origin.y + contentview.bounds.size.height + 20, 100, 30);
//    [monitor setTitle:FEString(@"SONSER_MONITOR") forState:UIControlStateNormal];
//    [scrollview addSubview:monitor];
    
    scrollview.contentSize = CGSizeMake(scrollview.bounds.size.width, configbutton.frame.origin.y + configbutton.bounds.size.height + 20);
    scrollview.autoresizingMask = UIViewAutoresizingFlexibleHeight;
    
}

-(void)addUserMark:(UIButton *)button{
    [self displayHUD:FEString(@"LOADING...")];
    
    FEUserMark *mark = [[FEUserMark alloc] initWithUserID:FELoginUser.userid mark:@"Test"];
    FEMarkSetRequest *markRequest = [[FEMarkSetRequest alloc] initWithMark:mark];
    [[FEWebServiceManager sharedInstance] markSet:markRequest response:^(NSError *error, FEBaseResponse *response) {
        [self hideHUD:YES];
        if (!error && response.result.errorCode.integerValue == 0) {
            NSLog(@"add mark success!");
        }
    }];
}

-(void)config:(id)sender{
    [self displayHUD:FEString(@"LOADING...")];
    __weak typeof(self) weakself = self;
    FESensor *sensor = [self.sensor copy];
    [sensor setValue:self.seletedMark.mark forKey:@"mark"];
    [sensor setValue:self.descTextField.text forKey:@"descriptions"];
    FESensorSetRequest *rdata = [[FESensorSetRequest alloc] initWithCommond:@(0) sensor:sensor];
    [[FEWebServiceManager sharedInstance] sensorSet:rdata response:^(NSError *error, FESensorSetResponse *response) {
        if (!error && response.result.errorCode.integerValue == 0) {
            [weakself.sensor setValue:weakself.seletedMark.mark forKey:@"mark"];
            [weakself.sensor setValue:self.descTextField.text forKey:@"descriptions"];
            if ([weakself.delegate respondsToSelector:@selector(sensorDidConfig)]) {
                [weakself.delegate sensorDidConfig];
            }
            [weakself.navigationController popViewControllerAnimated:YES];
            
        }
        [weakself hideHUD:YES];
    }];
}

#pragma mark - FEPopPickerViewDataSource
-(NSInteger)popPickerItemNumber{
    return self.markList.count;
}

-(NSString *)popPickerTitleAtIndex:(NSInteger)index{
    return ((FEUserMark *)self.markList[index]).mark;
}

#pragma mark - FEPopPickerViewDelegate
-(void)popPickerDidSelectedIndex:(NSInteger)index{
    self.seletedMark = self.markList[index];
}

#pragma override
-(void)keyboardWillHide:(CGRect)newRect duration:(NSTimeInterval)duration{
    self.scrollView.frame = self.view.bounds;
}

-(void)keyboardWillShow:(CGRect)newRect duration:(NSTimeInterval)duration{
    [UIView animateWithDuration:duration animations:^(void){
        CGRect frame = self.view.bounds;
        frame.size.height -= newRect.size.height;
        self.scrollView.frame = frame;
    }];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
