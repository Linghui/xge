#!/usr/bin/perl
use Socket qw(:all);
my $address = sockaddr_in(9876,inet_aton('localhost'));
socket(SERVER, AF_INET, SOCK_STREAM, IPPROTO_TCP) || die "socket create: $!\n";
setsockopt(SERVER, SOL_SOCKET, SO_REUSEADDR, 1) || die "socket reuse: $!\n";
bind(SERVER, $address) || die "socket bind: $!\n";
listen(SERVER, 5) || die "socket listen: $!\n";
while (1)
{
    my $c = '';
    accept(SOCK, SERVER) || die "accept: $!\n";
    sysread(SOCK, $c, 100) or die "recv: $!\n";
    print "$c\n";
    syswrite(SOCK, $c."back\n");
}