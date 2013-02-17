#!/usr/bin/perl -w
# 去重复字

use strict;
use warnings;

use Encode;
use Encode::CN;
my %hash = ();

my @all_lines = ();

while(<>){
    chomp;
    push @all_lines, $_;
}

for my $line (@all_lines){
    Encode::_utf8_on($line); 
    for( my $index = 0; $index < length $line; $index++ ){
        my $one = substr $line, $index, 1;
        $hash{$one}++;
    }
}

print keys %hash;
print "\n";
print STDERR scalar( keys %hash );
print "\n";
