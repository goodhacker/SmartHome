//
//  FEEWarringVC.m
//  SmartHome
//
//  Created by Seven on 15-4-1.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEEWarringVC.h"
#import "FEFireAlarmByCidRequest.h"
#import "FEFireAlarmResponse.h"
#import "FEWebServiceManager.h"
#import "FEMemoryCache.h"
#import "FECompany.h"
#import "FEDeviceDetailVC.h"

@interface FEEWarringVC ()<UITableViewDataSource,UITableViewDelegate>{
    NSMutableArray *_fireAlarm;
}
@property (strong, nonatomic) IBOutlet UITableView *tableView;

@end

@implementation FEEWarringVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    _fireAlarm = [NSMutableArray new];
    [self requestAlarm];
}

-(void)requestAlarm{
    if (self.company) {
        __weak typeof(self) weakself = self;
        FEAttribute *attr = [[FEAttribute alloc] initWithAttrName:@"alarmKind" value:@"2"];
        FEFireAlarmByCidRequest *rdata = [[FEFireAlarmByCidRequest alloc] initWithUid:[FEMemoryCache sharedInstance].user.userID comanyId:self.company.companyID page:[[FEPage alloc] initWithPageSize:0 currentPage:0 count:0] filterList:@[attr]];
        [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FEFireAlarmResponse class] response:^(NSError *error, id response) {
            FEFireAlarmResponse *rsp = response;
            if (!error && rsp.result.errorCode.integerValue == 0) {
                [_fireAlarm addObjectsFromArray:rsp.fireAlarmList];
                [weakself.tableView reloadData];
            }
        }];
    }
}


#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    FEFireAlarm *alarm = _fireAlarm[indexPath.row];
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"alarmItemCell" forIndexPath:indexPath];
    cell.detailTextLabel.text = alarm.locationDesp;
    cell.textLabel.text = [[alarm.sensorTypeName stringByAppendingString:@"发生"] stringByAppendingString:alarm.alarmTypeName];
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return _fireAlarm.count;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    FEFireAlarm *alarm = _fireAlarm[indexPath.row];
    [self performSegueWithIdentifier:@"toDeviceSegue" sender:alarm];
    
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
    FEDeviceDetailVC *vc = segue.destinationViewController;
    vc.company = self.company;
    vc.device = sender;
}


@end
