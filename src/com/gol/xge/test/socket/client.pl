#!/usr/bin/perl
use Socket qw(:all);
$address = sockaddr_in(9876,inet_aton('localhost'));
socket(SOCK, AF_INET, SOCK_STREAM, IPPROTO_TCP) || die $!;
connect(SOCK, $address) || die $!;
syswrite(SOCK, "gogonn\n");
while(<SOCK>)
{
    print;
}
close SOCK or die "close: $!";